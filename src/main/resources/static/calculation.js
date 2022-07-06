
class FenwFeatureTree {

    constructor(size){
        this.size = size
        this.tree = []
        for(let i = 0; i < size; i++){
            this.tree[i] = 0
        }
    }

    update(timeSlot,val){
        if(timeSlot == 0) return //must start at 1
        while (timeSlot < this.size){
            this.tree[timeSlot] += val
            timeSlot += timeSlot & (-timeSlot)
        }
    }



    query(timeSlot){

        let returnVal = 0;

        while (timeSlot > 0) {
             returnVal += this.tree[timeSlot]
            timeSlot -= timeSlot & (-timeSlot)
        }

        return returnVal

    }


    rangeQuery(l,r){
        let ret = this.query(l-1) - this.query(r)
        return ret
    }

    addTimeline(start,end){
        //Inside, prefix sum adds 1 because it encounters slot=start
        this.update(start,1);

        //When going outisde timeline (end+1), one add -1 to remember one dont longer
        //have added +1 when encountered start.
        // Only going to use prefix sum to count number of timelines that I am standing on, so
        // no range query needed
        //
        //--OFF TOPIC---
        //Range query gives then prefix sum to end - prefix sum to start:
        //NB! not needed for this class
        // (rangequery start inside timeline, rangequery end outside timeline): (1+(-1)) - 1 = -1
        // (rangequery start inside timeline, rangequery end inside timeline):  (1+0) - 1 = 0
        // (rangequery start outside timeline, rangequery end outside timeline):  (1+(-1)) - 0 = 0-0 =0
        // (rangequery start outside timeline, rangequery end inside timeline):  (1+0) - 0 = 1-0 =1
        //Because all timelines inside follow same logic, sum becomes 0.
        this.update(end+1,-1);

    }
    //Do reverse update compared to adding
    removeTimeline(start,end){
        this.update(start,-1);
        this.update(end+1,1);
    }

    getCountingList(start,stop){
        let res = []
        for(let i = start; i <= stop; i++){
            res.push(this.query(i))
        }
        return res;
    }





}
var timeLineModule = (function(){

    let timeLines = [];
    let fenwFeatureTree;
    let timestamp
    let startSelected;
    let stopSelected

    async function getInitPState(texttocommentid) {
        await $.post({
            url: '/textServlet',
            data: JSON.stringify({ "remoteMethod": "getInitState", "texttocommentid": texttocommentid}),
            contentType: "application/json; charset=utf-8"
        }).done((res) => {
            timestamp = new Date().valueOf();
            timeLines = [];
            this.fenwFeatureTree = new FenwFeatureTree(parseInt($("#textToComment").val().length))
            //this.timestamp = res.timestamp

            for (const [key, timeline] of Object.entries(res)){
                timeLines.push(timeline)
                this.fenwFeatureTree.addTimeline(timeline.start,timeline.end);
            }
        }).promise();

    }

    async function sendTimePLine(timeline) {
//
        await $.post({
            url: '/textServlet',
            data: JSON.stringify({ "remoteMethod": "addTimeLine", "timeline": timeline}),
            contentType: "application/json; charset=utf-8"
        }).done((res) => {

        }).promise();

    }

    async function removePTimeLineById(id) {
        let changeTime = new Date().valueOf()

        await $.post({
            url: '/textServlet',
            data: JSON.stringify({ "remoteMethod": "removeTimeline", "id": id,"timestampChanged":changeTime}),
            contentType: "application/json; charset=utf-8"
        }).done((res) => {

        }).promise();

    }

    async function changePTimeLineById(id,timeline) {
//
        await $.post({
            url: '/textServlet',
            data: JSON.stringify({ "remoteMethod": "changeTimeline","timeline":timeline, "id": id}),
            contentType: "application/json; charset=utf-8"
        }).done((res) => {


        }).promise();

    }
    function removeTimePLine(id){
        let index = timeLines.findIndex((x)=>{return x.id == id})
        timeLines.splice(index,1)
    }
    function changePLine(id,tidslinje){
        let index = timeLines.findIndex((x)=>{return x.id == id})
        timeLines.splice(index,tidslinje)
    }
    function updateColorMap(text){
        let nmbTimelines = timeLineModule.getCountingList(0,String(text).length);
        let html="";
        $("#textDensityMap").empty();
        [...(String(text))].forEach((x,idx)=>{

            if(nmbTimelines[idx] >= 32)
                html+="<span style='background-color:red'>" + x + "</span>";
            else if(nmbTimelines[idx] >= 16)
                html+="<span style='background-color:yellow'>" + x + "</span>";
            else if(nmbTimelines[idx] >= 8)
                html+="<span style='background-color:lightyellow'>" + x + "</span>";
            else if(nmbTimelines[idx] >= 4)
                html+="<span style='background-color:green'>" + x + "</span>";
            else if(nmbTimelines[idx] >= 2)
                html+="<span style='background-color:lightgreen'>" + x + "</span>";
            else if(nmbTimelines[idx] >= 1)
                html+="<span style='background-color:lightskyblue'>" + x + "</span>";
            else
                html+="<span style='background-color:white'>" + x + "</span>";

        });
        $(html).appendTo("#textDensityMap");
    }
    async function getPChanges(texttocommentid) {
        await $.post({
            url: '/textServlet',
            data: JSON.stringify({ "remoteMethod": "getChanges","texttocommentid":texttocommentid, "timestamp": timestamp}),
            contentType: "application/json; charset=utf-8"
        }).done((res) => {

               for (let key in res){
                   console.log(res[key].tidslinje)
                   if(String(res[key].command)=="ADD"){

                       timeLines.push(res[key].tidslinje)
                       this.fenwFeatureTree.addTimeline(res[key].tidslinje.start,res[key].tidslinje.end)

                   }
                   else if(String(res[key].command)=="CHANGE"){

                       let index = timeLines.findIndex((x)=>{return x.id == res[key].tidslinje.id})
                       timeLines.splice(index,1,res[key].tidslinje)
                   }
                   else if(String(res[key].command)=="REMOVE"){

                       let index = timeLines.findIndex((x)=>{return x.id == res[key].tidslinje.id})
                       timeLines.splice(index,1)
                       this.fenwFeatureTree.removeTimeline(res[key].tidslinje.start,res[key].tidslinje.end)

                   }
                   else {
                       console.log("ERROR--" + res[key])
                   }
               }

            timestamp = new Date().valueOf();
            updateColorMap(String($("#textToComment").val()));

            $( "#likes" ).val(timeLineModule.countLikes( startSelected, stopSelected,$("#percent").val() ) );
            $( "#dislikes" ).val(timeLineModule.countDisLikes( startSelected, stopSelected,$("#percent").val() ) );
            $("#comments").empty()
            obj=timeLineModule.filterListByTime(startSelected ,stopSelected,$("#percent").val() );
            let html = '';
            for (let key in obj) {
                html += '<div class="card bg-outline-info text-dark  mt-5" id='  + obj[key].id + '>';
//

                html += '<div class="card-body">';
                html += '<p style="background-color:lightskyblue">' +  " <strong>Commenting</strong>: " + String($('#textToComment')[0].value).substring(parseInt(obj[key].start),parseInt(obj[key].end))     + '</p>';
                html += '<p>' +  " <strong>id</strong>: " + obj[key].id + '</p>';
                html += '<p>' +  " <strong>user</strong>: " + obj[key].user + '</p>';
                html += '<p>' +  " <strong>timestampCreated</strong>: " + obj[key].timestampCreated + '</p>';
                html += '<p>' +  " <strong>timestampChanged</strong>: " + obj[key].timestampChanged + '</p>';
                html += '<p>' +  "<strong> text</strong>: " + obj[key].text + '</p>';
                html += '<p>' +  " <strong>like</strong>: " + obj[key].like + '</p>';
                html += '<p>' +  " <strong>dislike</strong>: " + obj[key].dislike + '</p>';
                html += '<p>' +  " <strong>deleted</strong>: " + obj[key].isdeleted + '</p>';
                html += '<button type="button" class="btn btn-info col-2 m-1 p-1" >Highlight commented text</button>';
                html += '<button type="button" data-bs-toggle="modal" data-bs-target="#myModal" class="btn btn-warning col-1 m-1 p-1">Change</button>';
                html += '<button type="button" class="btn btn-danger col-1 m-1 p-1" >Remove</button>';
                html += '</div>'
                html += '</div>'
            }

            $(html).appendTo("#comments");

        }).promise();


    }
    function filterPListByTime(start,end,percent){
        return timeLines.filter((x)=>{
            return x.start >= start && x.end <= end && ((x.start-x.end)/(start-end))*100 >= percent;
        })
    }

    function getPAllTimeLines(){
        return timeLines;
    }

    function extractPFeatures(l,r){
        return this.fenwFeatureTree.queryFeatures(l,r);
    }


    function countPLikes(start,end,percent){
        let timeLinesFilteredTime = filterPListByTime(start,end,percent);
        return timeLinesFilteredTime.reduce((nmbLikes,timeline)=>{
            if(timeline.like) return nmbLikes + 1;
            else              return nmbLikes;
        },0.0)
    }
    function countPDisLikes(start,end,percent){
        let timeLinesFilteredTime = filterPListByTime(start,end,percent);
        return timeLinesFilteredTime.reduce((nmbDisLike,timeline)=>{
            if(timeline.dislike) return nmbDisLike + 1;
            else              return nmbDisLike;
        },0.0)
    }
    function   initPFeatureTree(size){
        this.fenwFeatureTree = new FenwFeatureTree(size)
    }

    function updateP(timeslot,feature){
        //+1 because we need to start at 1 in fenwick
        this.fenwFeatureTree.addOne(timeslot+1,feature)
    }
    function rangeSearchP(liste,l,r){
        //+1 because we start at 1 in fenwick
        return this.fenwFeatureTree.rangeSearch(liste,l+1,r+1)
    }
    function filterPListByTimeAndUser(start,end,user){
        return timeLines.filter((x)=>{
            return x.start >= start && x.end <= end && x.user == user;
        })
    }

    function getPTimeLineById(id){
        let timeline = timeLines.find((x)=>{return x.id == id});
        return timeline
    }

    function extractChangedPTimeline() {
        let idToChange = $("#commentIdChange").val()

        //Deep copying, because we dont wanna change before server says its ok
        let timeline = JSON.parse(JSON.stringify(timeLineModule.getTimeLineById(idToChange)))

        timeline.id   = idToChange
        timeline.text = $("#commentCommentChange").val()
        timeline.user = $("#commentUserChange").val()
        timeline.like = $("#likeYesChange").is(':checked')
        timeline.dislike =  $("#dislikeYesChange").is(':checked')
        timeline.timestampChanged = new Date().valueOf()
        return timeline;
    }

    function setPStartSelect(selected){
        startSelected = selected;
    }

    function getPStartSelect(){
        return startSelected;
    }

    function setPStopSelect(selected){
        stopSelected = selected;
    }

    function  getPStopSelect(){
        return stopSelected;
    }
    function getPCountingList(start,stop){
        return this.fenwFeatureTree.getCountingList(start,stop)
    }
    function getPCount(pos){
        return this.fenwFeatureTree.query(pos);
    }
    function addPTimeLine(timeline){
        timeLines.push(timeline);

        $( "#likes" ).val(timeLineModule.countLikes( startSelected, stopSelected,$("#percent").val() ) );
        $( "#dislikes" ).val(timeLineModule.countDisLikes( startSelected, stopSelected,$("#percent").val() ) );
        $("#comments").empty()
        obj=timeLineModule.filterListByTime(startSelected ,stopSelected);
        let html = '';
        for (let key in obj) {
            html += '<div class="card bg-outline-info text-dark  mt-5" id='  + obj[key].id + '>';

            html += '<div class="card-body">';
            html += '<p style="background-color:lightskyblue">' +  " <strong>Commenting</strong>: " + String($('#textToComment')[0].value).substring(parseInt(obj[key].start),parseInt(obj[key].end))     + '</p>';
            html += '<p>' +  " <strong>id</strong>: " + obj[key].id + '</p>';
            html += '<p>' +  " <strong>user</strong>: " + obj[key].user + '</p>';
            html += '<p>' +  " <strong>timestampCreated</strong>: " + obj[key].timestampCreated + '</p>';
            html += '<p>' +  " <strong>timestampChanged</strong>: " + obj[key].timestampChanged + '</p>';
            html += '<p>' +  "<strong> text</strong>: " + obj[key].text + '</p>';
            html += '<p>' +  " <strong>like</strong>: " + obj[key].like + '</p>';
            html += '<p>' +  " <strong>dislike</strong>: " + obj[key].dislike + '</p>';
            html += '<p>' +  " <strong>deleted</strong>: " + obj[key].isdeleted + '</p>';
            html += '<button type="button" class="btn btn-info col-2 m-1 p-1" >Highlight commented text</button>';
            html += '<button type="button" data-bs-toggle="modal" data-bs-target="#myModal" class="btn btn-warning col-1 m-1 p-1">Change</button>';
            html += '<button type="button" class="btn btn-danger col-1 m-1 p-1" >Remove</button>';
            html += '</div>'
            html += '</div>'
        }

        $(html).appendTo("#comments");
    }

    return {
        filterListByTime: function (start,end,percent){
            return filterPListByTime(start,end,percent)
        }
        ,
        getAllTimeLines: function(){
            return getPAllTimeLines()
        }
        ,
        countLikes: function(start,end,percent){
            return countPLikes(start,end,percent)
        },
        countDisLikes: function(start,end,percent){
            return countPDisLikes(start,end,percent)
        },
        filterListByTimeAndUser:  function(start,end,user){
            filterListByTimeAndUser(start,end,user)
        },
        getTimeLine: function(commentId){
            getPTimeLine(commentId)
        } ,
        getInitState : async function (texttocommentid){
            await getInitPState(texttocommentid).then(function (res) {

            }).catch(function (err) {})
        } ,
        sendTimeLine : async function (timeline){
            await sendTimePLine(timeline).then(function (res) {
                timeLineModule.getChanges(parseInt($("#currentTitleId").val()))
            }).catch(function (err) {})

        } ,
        getChanges : async function (texttocommentid){
            await getPChanges(texttocommentid).then(function (res) {

            }).catch(function (err) {})
        },
        extractTidslinje: function(){


            let tidslinjeData = {
                id: -1,
                user:   $("#commentUser").val().trim(),
                timestampCreated: new Date().valueOf(),
                timestampChanged: new Date().valueOf(),
                start: startSelected,
                end: stopSelected,
                text:  $("#commentComment").val().trim(),
                like: $("#likeYes").is(':checked'),
                dislike: $("#dislikeYes").is(':checked'),
                isdeleted:false,
                texttocommentid: parseInt($("#currentTitleId").val().trim())

            }

            return tidslinjeData;

        },//
        extractChangedTimeline: function(){
            return extractChangedPTimeline()


        },

        getTimeLineById : function(id){
            return getPTimeLineById(id)
        },
        removeTimeLineById :  function(id) {
             removePTimeLineById(id).then(function (res) {
                 timeLineModule.getChanges(parseInt($("#currentTitleId").val()))

            }).catch(function (err) {
                 timeLineModule.getChanges(parseInt($("#currentTitleId").val()))
             });
        },

        changeTimeLineById : async function(id,timeline){
            await changePTimeLineById(id,timeline).then(function (res) {
                    timeLineModule.getChanges(parseInt($("#currentTitleId").val()));
            }).catch(function (err) { timeLineModule.getChanges(parseInt($("#currentTitleId").val()))})

        },
        initFeatureTree: function(size){
            initPFeatureTree(size)
        },
        update: function(timeslot){
            updateP(timeslot)
        },
        rangeSearch: function (){
            let start=startSelected
            let end=stopSelected
            let liste = $( "#featuresToFind" ).val().split(",")
            let res = rangeSearchP(liste,start+1,end+1)
            return [res[0]-1,res[1]-1]
        },
        setStartSelect: function(startSelect){
            setPStartSelect(startSelect)
        },
        getStartSelect: function(){
            return getPStartSelect();
        },
        setStopSelect: function(stopSelect){
                setPStopSelect(stopSelect)
        },
        getStopSelect: function (){
                return getPStopSelect();
        },
        getCountingList: function(start,stop){
            return getPCountingList(start,stop)
        },
        getCount: function(pos){
            return getPCount(pos);
        }


    }
})();