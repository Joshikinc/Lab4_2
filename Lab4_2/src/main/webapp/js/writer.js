let Game = {
	name: "",
	genre: "",
	platform: "",
	year: 0,
	price: 0
	}
function getData(){
    Game.name = document.getElementById("name").value;
    Game.genre = document.getElementById("genre").value;
    Game.platform = document.getElementById("platform").value;
    Game.year = document.getElementById("year").value;
    Game.price = document.getElementById("price").value;
    let gameJson = JSON.stringify(Game);
    let xhrWrite = new XMLHttpRequest();
    xhrWrite.open("POST", "write",true);
	xhrWrite.setRequestHeader('Content-Type', 'application/json');
    xhrWrite.send(gameJson);
};