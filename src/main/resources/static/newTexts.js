
var textModule = (function(){
    let titles = [];

    async function addPNewText(title,text) {

        await $.post({
            url: '/newTextServlet',
            data: JSON.stringify({ "remoteMethod": "addText","title": title, "text": text}),
            contentType: "application/json; charset=utf-8"
        }).done((res) => {

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

    return {
        getTitles: function(){
            return getPTitles();
        },
        addNewText: function (title,text){
            return addPNewText(title,text)
        }

    }
})();