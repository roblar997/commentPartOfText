
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
            return addPNewText(title,text)
        },
        getText: function (title){
            return getPText(title);
        }

    }
})();