var pn = 1;
var itemsPerPage = 0;
var back = 0;
var next = 0;
var lastPage = 0;
var nr = 0;
var container = null;
var docs = null;
var enteredTime = 0;
var searchDocs = [];
var docsObj = {};
var digitRegex = /^\d+$/;

$(window).load(function(){
	container.show();
});

$(document).ready(function() {
	
    container = $('#lis_mantenimiento').find('tbody');    
    docs = container.find('tr');
    nr = docs.length;
    
    docsObj = docs.map(function(i, v) {
        var $td = $('td', this);        
        return {
            id: ++i,
            c1: $.trim($td.eq(1).html()!=null?($td.eq(1).html().indexOf("<")!=-1?'':$td.eq(1).html()):''),
            c2: $.trim($td.eq(2).html()!=null?($td.eq(2).html().indexOf("<")!=-1?'':$td.eq(2).html()):''),
            c3: $.trim($td.eq(3).html()!=null?($td.eq(3).html().indexOf("<")!=-1?'':$td.eq(3).html()):''),
            c4: $.trim($td.eq(4).html()!=null?($td.eq(4).html().indexOf("<")!=-1?'':$td.eq(4).html()):''),
            c5: $.trim($td.eq(5).html()!=null?($td.eq(5).html().indexOf("<")!=-1?'':$td.eq(5).html()):''),
            c6: $.trim($td.eq(6).html()!=null?($td.eq(6).html().indexOf("<")!=-1?'':$td.eq(6).html()):''),
            c7: $.trim($td.eq(7).html()!=null?($td.eq(7).html().indexOf("<")!=-1?'':$td.eq(7).html()):'')
        };
    }).get();
    
    configPage();
    sortDocs('page');
    createPaginator();
    
    $('body').on('click', '.page', function() {
        pn = $(this).data('page');
        configPage();
        sortDocs($(this).data('type'));
        createPaginator();
    });

    $('#items-per-page').change(function() {
        pn = 1;
        itemsPerPage = $(this).val();
        configPage();
        sortDocs('page');
        createPaginator();
    });

    $('#txt-search').keyup(function(e) {
        if ($.trim($(this).val()) != '' ) {
            var keyCode = (window.event) ? e.which : e.keyCode;
            if (keyCode == 13) {
            	
                var results = jlinq.from(docsObj)
                    .ignoreCase()
                    .contains('c1', $(this).val().trim())
                    .or('c2', $(this).val().trim())
                    .or('c3', $(this).val().trim())
                    .or('c4', $(this).val().trim())
                    .or('c5', $(this).val().trim())
                    .or('c6', $(this).val().trim())
                    .or('c7', $(this).val().trim())
                    .sort('id')
                    .select();
                container.html('');
                if (results.length != 0) {
                	searchDocs = [];
                    $.each(results, function(i, tr) {
                        searchDocs.push(docs.eq(tr.id - 1).get(0));
                    });
                    pn = 1;
                    nr = searchDocs.length;
                    configPage();
                    sortDocs('page');
                    createPaginator();
                } else {
                	
                    searchDocs = null;
                    pn = 1;
                    nr = 0;
                    configPage();
                    sortDocs('page');
                    createPaginator();
                }
            }
        } else {
        	
        	var results = jlinq.from(docsObj)
            .ignoreCase()
            .contains('c1', $(this).val().trim())
            .or('c2', $(this).val().trim())
            .or('c3', $(this).val().trim())
            .or('c4', $(this).val().trim())
            .or('c5', $(this).val().trim())
            .or('c6', $(this).val().trim())
            .or('c7', $(this).val().trim())
            .sort('id')
            .select();
         	
        	searchDocs = [];
            
        	$.each(results, function(i, tr) {
            	searchDocs.push(docs.eq(tr.id - 1).get(0));
            });
        	
            pn = 1;
            nr = docs.length;
            configPage();
            sortDocs('page');
            createPaginator();
        }
    });

    $('#txt-page').keyup(function(e) {
        var keyCode = (window.event) ? e.which : e.keyCode;
        if (keyCode === 13) {
            var tmpPn = $.trim($(this).val());
            if (digitRegex.test(tmpPn)) {
                if (tmpPn.length != 0) {
                    if (tmpPn != 0) {
                        pn = parseInt(tmpPn);
                        configPage();
                        sortDocs('page');
                        createPaginator();
                    }
                }
            }
        }
    });
    
});

function configPage() {
    itemsPerPage = $('#items-per-page').val();
    lastPage = Math.ceil(nr / itemsPerPage);

    if (pn < 1) {
        pn = 1;
    } else if (pn > lastPage) {
        pn = lastPage;
    }
}

function sortDocs(type) {
    var pageDocs = null;
    
    if(searchDocs != null) {
	    if (type === 'page') {
	        if (searchDocs.length != 0) {
	            if (pn === 1) {
	                pageDocs = searchDocs.slice(0, (itemsPerPage * pn));
	            } else {
	                ++enteredTime;
	                pageDocs = searchDocs.slice(itemsPerPage * (pn - 1), (itemsPerPage * pn));
	            }
	        } else {
	            if (pn === 1) {
	                pageDocs = docs.slice(0, (itemsPerPage * pn));
	            } else {
	                ++enteredTime;
	                pageDocs = docs.slice(itemsPerPage * (pn - 1), (itemsPerPage * pn));
	            }
	        }
	    } else if (type === 'back') {
	        if (searchDocs.length != 0) {
	            if (enteredTime === 0) {
	                pageDocs = searchDocs.slice(itemsPerPage, (itemsPerPage * pn));
	            } else {
	                --enteredTime;
	                pageDocs = searchDocs.slice(itemsPerPage * (pn - 1), (itemsPerPage * pn));
	            }
	        } else {
	            if (enteredTime === 0) {
	                pageDocs = docs.slice(itemsPerPage, (itemsPerPage * pn));
	            } else {
	                --enteredTime;
	                pageDocs = docs.slice(itemsPerPage * (pn - 1), (itemsPerPage * pn));
	            }
	        }
	    } else if (type === 'next') {
	        ++enteredTime;
	        if (searchDocs.length != 0) {
	            if (enteredTime === 1) {
	                pageDocs = searchDocs.slice(itemsPerPage, (itemsPerPage * pn));
	            } else {
	                pageDocs = searchDocs.slice(itemsPerPage * (pn - 1), (itemsPerPage * pn));
	            }
	        } else {
	            if (enteredTime === 1) {
	                pageDocs = docs.slice(itemsPerPage, (itemsPerPage * pn));
	            } else {
	                pageDocs = docs.slice(itemsPerPage * (pn - 1), (itemsPerPage * pn));
	            }
	        }
	    }
    }
    
    var xdocs = '';
    if(pageDocs != null){
	    for(var i in pageDocs) { 	
	    	if(i < pageDocs.length){
	    		xdocs = xdocs + pageDocs[i].outerHTML;
	    	}
	    }
    }
    container.html(xdocs);
    
}

function createPaginator() {

    var centerPages = '';
    var sub1 = pn - 1;
    var sub2 = pn - 2;
    var add1 = pn + 1;
    var add2 = pn + 2;

    if (pn === 1) {
        centerPages += '&nbsp; <span>' + pn + '</span> &nbsp;';
        centerPages += '&nbsp; <a href="javascript:void(0);" class="page" data-page="' + add1 + '" data-type="page">' + add1 + '</a> &nbsp;';
    } else if (pn === lastPage) {    	
        centerPages += '&nbsp; <a href="javascript:void(0);" class="page" data-page="' + sub1 + '" data-type="page">' + sub1 + '</a> &nbsp;';
        centerPages += '&nbsp; <span>' + pn + '</span> &nbsp;';
    } else if (pn > 2 && pn < (lastPage - 1)) {    	
        centerPages += '&nbsp; <a href="javascript:void(0);" class="page" data-page="' + sub2 + '" data-type="page">' + sub2 + '</a> &nbsp;';
        centerPages += '&nbsp; <a href="javascript:void(0);" class="page" data-page="' + sub1 + '" data-type="page">' + sub1 + '</a> &nbsp;';
        centerPages += '&nbsp; <span>' + pn + '</span> &nbsp;';
        centerPages += '&nbsp; <a href="javascript:void(0);" class="page" data-page="' + add1 + '" data-type="page">' + add1 + '</a> &nbsp;';
        centerPages += '&nbsp; <a href="javascript:void(0);" class="page" data-page="' + add2 + '" data-type="page">' + add2 + '</a> &nbsp;';
    } else if (pn > 1 && pn < lastPage) {
        centerPages += '&nbsp; <a href="javascript:void(0);" class="page" data-page="' + sub1 + '" data-type="page">' + sub1 + '</a> &nbsp;';
        centerPages += '&nbsp; <span>' + pn + '</span> &nbsp;';
        centerPages += '&nbsp; <a href="javascript:void(0);" class="page" data-page="' + add1 + '" data-type="page">' + add1 + '</a> &nbsp;';
    }

    var paginationDisplay = '';

    if (nr != 0) {
        if (lastPage != 1) {
            paginationDisplay += '<div style="float:left">P\u00E1g. <strong>' + pn + '</strong> de ' + lastPage + '</div>';
            if (pn != 1) {
                back = pn - 1;
                paginationDisplay += '&nbsp; <a href="javascript:void(0);" class="page" style="text-decoration:none;" data-page="' + back + '" data-type="back"><</a>';
            }
            paginationDisplay += '<span>' + centerPages + '</span>';
            if (pn != lastPage) {            	
                next = pn + 1;
                paginationDisplay += '&nbsp; <a href="javascript:void(0);" class="page" style="text-decoration:none;" data-page="' + next + '" data-type="next">></a>';
            }
            $('#paginator').html('<div style="font-size:11px; text-align:right;" class="lfooter">' + paginationDisplay + '</div>');
        }
        else {        	
            paginationDisplay = 'P\u00E1g. <b>1</b> de <b>1</b>';
            $('#paginator').html('<div style="font-size:11px; text-align:right;" class="lfooter">' + paginationDisplay + '</div>');
        }
    }
    else {    	
        paginationDisplay = '';
        $('#paginator').html('<div style="font-size:11px; text-align:right; text-align:center;" class="lfooter">0 Resultados</div>');
    }
}