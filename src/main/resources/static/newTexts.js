
var textModule = (function(){
    let titles = [];
    let text;

    async function addPNewText(title,text) {

        await $.post({
            url: '/newTextServlet',
            data: JSON.stringify({ "remoteMethod": "addText","title": title, "text": text}),
            contentType: "application/json; charset=utf-8"
        }).done((res) => {

        }).promise();

    }
    async function getText(title) {

        await $.post({
            url: '/newTextServlet',
            data: JSON.stringify({ "remoteMethod": "getText","title": title}),
            contentType: "application/json; charset=utf-8"
        }).done((res) => {
            text = res;
        }).promise();

    }
    async function deletePText(title) {

        await $.post({
            url: '/newTextServlet',
            data: JSON.stringify({ "remoteMethod": "deleteText","title": title}),
            contentType: "application/json; charset=utf-8"
        }).done(async (res) => {
            $("#selectTitles").empty()
            //bad solution for now. Loading everything, and not only the changes
            let titles = await textModule.getTitles();
            let html = "";
            titles.forEach((res) => {
                html += "<option value='" + res + "'>" + res + "</option>";
            });

            $(html).appendTo("#selectTitles");
        }).promise();

    }
    async function getTitlesFromServer() {

        await $.post({
            url: '/newTextServlet',
            data: JSON.stringify({ "remoteMethod": "getTitles" }),
            contentType: "application/json; charset=utf-8"
        }).done((res) => {
                titles = res;
        }).promise();

    }
    async  function  getPTitles(){
        await getTitlesFromServer();
        return titles;
    }
    async  function  getPText(title){
        await getText(title);
        return text;
    }

    return {
        getTitles: function(){
            return getPTitles();
        },
        addNewText: function (title,text){
            addPNewText(title,text).then().catch();
        },
        addNewText: function (newText){
            addPNewText(newText.title,newText.text).then().catch();
        },
        getText: function (title){
            return getPText(title);
        },
        deleteText: function (title){
            deletePText(title).then().catch();
        },
        extractNewText: function (){
            let newText = {
                title:$("#textTitle").val().trim(),
                text: $("#textToCreate").val().trim(),
            }
            return newText;
        }

    }
})();