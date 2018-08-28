/**
 * This will manage our library
 */
$(document).ready(()=>{
    
    //definitions ======================================================================
    let previousBtnDisabled = true;
    let nextBtnDisabled = false;

    let countPages = function(){
        return $("#pagination_list > li").length - 2;
    }

    let currentPageButton = function(){
        return $("#pagination_list").children(".active").children("a");
    }

    //fill page with the downloaded books
    let populatePage = function(books_array){
        $("#library_page").empty();
        for(let i=0; i<books_array.length; i++){
            $("#library_page").append(`<div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${books_array[i].title}</h5>
                        <p class="card-text">Written by ${books_array[i].author} at ${books_array[i].publishDate}</p>
                        <a href="#" class="btn btn-danger">Delete</a>
                    </div>
                </div>`);
        }
    }

    //when a page button is pressed
    $(".page_btn").click(function(){
        $("#pagination_list").children(".active").removeClass("active");
        $(this).parent().addClass("active"); 
        let text = $(this).text();
        //disable or enable previous and next buttons, depending on the current page
        let pages = countPages();
        if(pages == 1){ //disable previous and next buttons if only one page exists
            $("#page_btn_prev").parent().addClass("disabled");
            previousBtnDisabled = true;
            $("#page_btn_next").parent().addClass("disabled");
            nextBtnDisabled = true;
        }
        //more than one pages exist
        else if(text == "1"){ //we are at the first page
            $("#page_btn_prev").parent().addClass("disabled");
            previousBtnDisabled = true;
            if(nextBtnDisabled){
                $("#page_btn_next").parent().removeClass("disabled");
                nextBtnDisabled = false;
            }
        }else{
            if(previousBtnDisabled){
                $("#page_btn_prev").parent().removeClass("disabled");
                previousBtnDisabled = false;
            }
            if(parseInt(text) == pages){ //we are at the last page
                $("#page_btn_next").parent().addClass("disabled");
                nextBtnDisabled = true;
            }else{ //we are neither at the first or the last page
                if(nextBtnDisabled){
                    $("#page_btn_next").parent().removeClass("disabled");
                    nextBtnDisabled = false;
                }
            }
        }
        let url = "library/"+$(this).text(); //request the specific page
        $.get(url, (books_array)=>{
            populatePage(books_array);
        },"json").fail(()=>{
            alert("Error in downloading data");
        });
    });

    //when the previous button is pressed
    $("#page_btn_prev").click(function(){
        var currButton = currentPageButton();
        currButton.parent().prev().children(".page_btn").click();
    });

    //when the next button is pressed
    $("#page_btn_next").click(function(){
        var currButton = currentPageButton();
        currButton.parent().next().children(".page_btn").click();
    });

    //invocations ===========================================================
	countPages();
});
