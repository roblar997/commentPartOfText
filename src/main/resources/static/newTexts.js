
var textModule = (function(){
    let texts = [];

    async function addPNewText(text) {

        await $.post({
            url: '/newTextServlet',
            data: JSON.stringify({ "remoteMethod": "addText", "text": text}),
            contentType: "application/json; charset=utf-8"
        }).done((res) => {

        }).promise();

    }
    async function getTextsFromServer() {

        await $.post({
            url: '/newTextServlet',
            data: JSON.stringify({ "remoteMethod": "getTexts" }),
            contentType: "application/json; charset=utf-8"
        }).done((res) => {
            for (const [key, value] of Object.entries(res)){

                texts = res;
            }
        }).promise();

    }
    async  function  getPTexts(){
        await getTextsFromServer();
        return texts;
    }

    return {
        getTexts: function(){
            return getPTexts();
        },
        addNewText: function (text){
            return addPNewText(text)
        }

    }
})();