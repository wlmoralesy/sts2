var ppn = 1;
var pitemsPerPage = 0;
var pback = 0;
var pnext = 0;
var plastPage = 0;
var pnr = 0;
var pcontainer = null;
var pdocs = null;
var penteredTime = 0;
var psearchDocs = [];
var pdocsObj = {};
var pdigitRegex = /^\d+$/;

$(window).load(function(){
	pcontainer.show();
});

$(document).ready(function() {
	
    pcontainer = $('#lis_producto').find('tbody');    
    pdocs = pcontainer.find('tr');
    pnr = pdocs.length;
    
    pdocsObj = pdocs.map(function(i, v) {
        var $td = $('td', this);        
        return {
            id: ++i,
            nombre: $.trim($td.eq(1).html()),
            tipo: $.trim($td.eq(2).html()),
            comision: $.trim($td.eq(4).html()),
            sustancia: $.trim($td.eq(3).html())
        };
    }).get();
    
    pconfigPage();
    psortDocs('page');
    pcreatePaginator();
    
    $('body').on('click', '.pagep', function() {
        ppn = $(this).data('page');
        pconfigPage();
        sortDocs($(this).data('type'));
        pcreatePaginator();
    });

    $('#items-per-pagep').change(function() {
        ppn = 1;
        pitemsPerPage = $(this).val();
        pconfigPage();
        psortDocs('page');
        pcreatePaginator();
    });

    $('#txt-searchp').keyup(function(e) {
    	
        if ($.trim($(this).val()) != '' ) {
            var keyCode = (window.event) ? e.which : e.keyCode;
            if (keyCode == 13) {
            	
                var results = jlinq.from(pdocsObj)
                    .ignoreCase()
                    .contains('nombre', $(this).val().trim())
                    .or('tipo', $(this).val().trim())
                    .or('comision', $(this).val().trim())
                    .or('sustancia', $(this).val().trim())
                    .sort('id')
                    .select();
                pcontainer.html('');
                if (results.length != 0) {
                	psearchDocs = [];
                    $.each(results, function(i, tr) {
                        psearchDocs.push(pdocs.eq(tr.id - 1).get(0));
                    });
                    ppn = 1;
                    pnr = psearchDocs.length;
                    pconfigPage();
                    psortDocs('page');
                    pcreatePaginator();
                } else {
                	
                    psearchDocs = null;
                    ppn = 1;
                    pnr = 0;
                    pconfigPage();
                    psortDocs('page');
                    pcreatePaginator();
                }
            }
        } else {
        	
        	var results = jlinq.from(pdocsObj)
            .ignoreCase()
            .contains('nombre', $(this).val().trim())
            .or('tipo', $(this).val().trim())
            .or('comision', $(this).val().trim())
            .or('sustancia', $(this).val().trim())
            .sort('id')
            .select();
         	
        	psearchDocs = [];
            
        	$.each(results, function(i, tr) {
            	psearchDocs.push(pdocs.eq(tr.id - 1).get(0));
            });
        	
            ppn = 1;
            pnr = pdocs.length;
            pconfigPage();
            psortDocs('page');
            pcreatePaginator();
        }
    });

    $('#txt-pagep').keyup(function(e) {
        var keyCode = (window.event) ? e.which : e.keyCode;
        if (keyCode === 13) {
            var tmpPn = $.trim($(this).val());
            if (pdigitRegex.test(tmpPn)) {
                if (tmpPn.length != 0) {
                    if (tmpPn != 0) {
                        ppn = parseInt(tmpPn);
                        pconfigPage();
                        psortDocs('page');
                        pcreatePaginator();
                    }
                }
            }
        }
    });
    
});

function pconfigPage() {
    pitemsPerPage = $('#items-per-pagep').val();
    plastPage = Math.ceil(pnr / pitemsPerPage);

    if (ppn < 1) {
        ppn = 1;
    } else if (ppn > plastPage) {
        ppn = plastPage;
    }
}

function psortDocs(type) {
    var pageDocs = null;
    
    if(psearchDocs != null) {
	    if (type === 'page') {
	        if (psearchDocs.length != 0) {
	            if (ppn === 1) {
	                pageDocs = psearchDocs.slice(0, (pitemsPerPage * ppn));
	            } else {
	                ++penteredTime;
	                pageDocs = psearchDocs.slice(pitemsPerPage * (ppn - 1), (pitemsPerPage * ppn));
	            }
	        } else {
	            if (ppn === 1) {
	                pageDocs = pdocs.slice(0, (pitemsPerPage * ppn));
	            } else {
	                ++penteredTime;
	                pageDocs = pdocs.slice(pitemsPerPage * (ppn - 1), (pitemsPerPage * ppn));
	            }
	        }
	    } else if (type === 'pback') {
	        if (psearchDocs.length != 0) {
	            if (penteredTime === 0) {
	                pageDocs = psearchDocs.slice(pitemsPerPage, (pitemsPerPage * ppn));
	            } else {
	                --penteredTime;
	                pageDocs = psearchDocs.slice(pitemsPerPage * (ppn - 1), (pitemsPerPage * ppn));
	            }
	        } else {
	            if (penteredTime === 0) {
	                pageDocs = pdocs.slice(pitemsPerPage, (pitemsPerPage * ppn));
	            } else {
	                --penteredTime;
	                pageDocs = pdocs.slice(pitemsPerPage * (ppn - 1), (pitemsPerPage * ppn));
	            }
	        }
	    } else if (type === 'pnext') {
	        ++penteredTime;
	        if (psearchDocs.length != 0) {
	            if (penteredTime === 1) {
	                pageDocs = psearchDocs.slice(pitemsPerPage, (pitemsPerPage * ppn));
	            } else {
	                pageDocs = psearchDocs.slice(pitemsPerPage * (ppn - 1), (pitemsPerPage * ppn));
	            }
	        } else {
	            if (penteredTime === 1) {
	                pageDocs = pdocs.slice(pitemsPerPage, (pitemsPerPage * ppn));
	            } else {
	                pageDocs = pdocs.slice(pitemsPerPage * (ppn - 1), (pitemsPerPage * ppn));
	            }
	        }
	    }
    }
    
    var xpdocs = '';
    if(pageDocs != null){
	    for(var i in pageDocs) { 	
	    	if(i < pageDocs.length){
	    		xpdocs = xpdocs + pageDocs[i].outerHTML;
	    	}
	    }
    }
    pcontainer.html(xpdocs);
    
}

function pcreatePaginator() {

    var centerPages = '';
    var sub1 = ppn - 1;
    var sub2 = ppn - 2;
    var add1 = ppn + 1;
    var add2 = ppn + 2;

    if (ppn === 1) {
        centerPages += '&nbsp; <span>' + ppn + '</span> &nbsp;';
        centerPages += '&nbsp; <a href="javascript:void(0);" class="pagep" data-page="' + add1 + '" data-type="page">' + add1 + '</a> &nbsp;';
    } else if (ppn === plastPage) {
        centerPages += '&nbsp; <a href="javascript:void(0);" class="pagep" data-page="' + sub1 + '" data-type="page">' + sub1 + '</a> &nbsp;';
        centerPages += '&nbsp; <span>' + ppn + '</span> &nbsp;';
    } else if (ppn > 2 && ppn < (plastPage - 1)) {
        centerPages += '&nbsp; <a href="javascript:void(0);" class="pagep" data-page="' + sub2 + '" data-type="page">' + sub2 + '</a> &nbsp;';
        centerPages += '&nbsp; <a href="javascript:void(0);" class="pagep" data-page="' + sub1 + '" data-type="page">' + sub1 + '</a> &nbsp;';
        centerPages += '&nbsp; <span>' + ppn + '</span> &nbsp;';
        centerPages += '&nbsp; <a href="javascript:void(0);" class="pagep" data-page="' + add1 + '" data-type="page">' + add1 + '</a> &nbsp;';
        centerPages += '&nbsp; <a href="javascript:void(0);" class="pagep" data-page="' + add2 + '" data-type="page">' + add2 + '</a> &nbsp;';
    } else if (ppn > 1 && ppn < plastPage) {
        centerPages += '&nbsp; <a href="javascript:void(0);" class="pagpe" data-page="' + sub1 + '" data-type="page">' + sub1 + '</a> &nbsp;';
        centerPages += '&nbsp; <span>' + ppn + '</span> &nbsp;';
        centerPages += '&nbsp; <a href="javascript:void(0);" class="pagep" data-page="' + add1 + '" data-type="page">' + add1 + '</a> &nbsp;';
    }

    var paginationDisplay = '';

    if (pnr != 0) {
        if (plastPage != 1) {
            paginationDisplay += '<div style="float:left">P\u00E1g. <strong>' + ppn + '</strong> de ' + plastPage + '</div>';
            if (ppn != 1) {
                pback = ppn - 1;
                paginationDisplay += '&nbsp; <a href="javascript:void(0);" class="pagep" style="text-decoration:none;" data-page="' + pback + '" data-type="back"><</a>';
            }
            paginationDisplay += '<span>' + centerPages + '</span>';
            if (ppn != plastPage) {
                pnext = ppn + 1;
                paginationDisplay += '&nbsp; <a href="javascript:void(0);" class="pagep" style="text-decoration:none;" data-page="' + pnext + '" data-type="next">></a>';
            }
            $('#paginatorp').html('<div style="font-size:11px; text-align:right;" class="lfooter">' + paginationDisplay + '</div>');
        }
        else {
            paginationDisplay = 'P\u00E1g. <b>1</b> de <b>1</b>';
            $('#paginatorp').html('<div style="font-size:11px; text-align:right;" class="lfooter">' + paginationDisplay + '</div>');
        }
    }
    else {
        paginationDisplay = '';
        $('#paginatorp').html('<div style="font-size:11px; text-align:right; text-align:center;" class="lfooter">0 Resultados</div>');
    }
}