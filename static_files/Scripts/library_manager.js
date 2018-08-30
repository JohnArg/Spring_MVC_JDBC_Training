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
            let publishDate = moment(books_array[i].publishDate).format("D/M/YYYY"); //use moment.js to manipulate how the date will show
            $("#library_page").append(`<div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${books_array[i].title}</h5>
                        <p class="card-text">Written by ${books_array[i].author} at ${publishDate}</p>
                        <div class="card_btn_container">
                            <a href="#" class="btn btn-primary">Read</a>
                            <a href="#" class="btn btn-danger delete_book_btn" id="${books_array[i].id}">Delete</a>
                        </div>
                    </div>
                </div>`);
        }
    }

    //request a page's data, then fill the page
    let refreshPage = function(page){
        let url = "library/"+page; //request the specific page
        $.get(url, (books_array)=>{
            populatePage(books_array);
        },"json").fail(()=>{
            alert("Error in downloading data");
        });
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
        //request the specific page
        refreshPage($(this).text());
    });


    //when the add book button is pressed
    $("#modalOpener").click(function(){
        $('#addModal').modal('show');
    });

    //when the submit button is pressed on the modal
    $("#add_book_btn").click(function(){
        let title = $("#input_title").val();
        let author = $("#input_author").val();
        let publishDate = $("#input_date").val();
        $.post("add_book", {title, author, publishDate}, (data)=>{
        
        }).fail(()=>{
            alert("Error in adding book");
        });    
        $('#addModal').modal('hide');
        location.reload();
    });

    //when the delete button is pressed on a book
    $(document).on("click",".delete_book_btn", function(){
        $.post("delete_book", {id: $(this).prop("id")}, (data)=>{
        
        }).fail(()=>{
            alert("Error in deleting book");
        });    
        $('#addModal').modal('hide');
        location.reload();
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
