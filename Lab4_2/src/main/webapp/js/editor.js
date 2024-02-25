let Game = {
	name: "",
	genre: "",
	platform: "",
	year: 0,
	price: 0,
	idInDatabase:0
}
function sendEditedGame(){
	let xhrEdit = new XMLHttpRequest();
	Game.name=document.getElementById("name").value;
	Game.genre=document.getElementById("genre").value;
	Game.platform=document.getElementById("platform").value;
	Game.year=document.getElementById("year").value;
	Game.price=document.getElementById("price").value;
	Game.idInDatabase=location.search.split("=")[1];
	let gameJson = JSON.stringify(Game);
	console.log(Game);
	xhrEdit.open("POST", "saveChanges",true);
	xhrEdit.setRequestHeader('Content-Type', 'application/json');
	xhrEdit.send(gameJson);
    location.href="read"
}