/*
This file will manage the home page
*/
$(document).ready(()=>{
    
    let date = new Date();
    let day = date.getDate();
    let month = date.getMonth();
    let year = date.getFullYear();
    
    $("#timer").text(`Current date : ${day} / ${month + 1} / ${year}`);
	
	
});