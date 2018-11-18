
window.callService = function(apiName, callback , request){
	
	var ajaxConfig = {
		url : apiName,
		dataType: "JSON",
		contentType: "application/json; charset=utf-8"
	}
	
	if(request){
		ajaxConfig['method'] = 'POST';
		ajaxConfig['data'] = JSON.stringify(request);
	}
	
	$.ajax(ajaxConfig).done(callback);
}

window.handleAllProductsData = function(data){
	window.data = data.allProducts;
	var products = data.allProducts;
	
	var table_data = '';

	for(var key in products ){
		var row = '<tr>' +
				  '<td>' + products[key].name  +'</td>' +
				  '<td>' + products[key].description  +'</td>' +
				  '<td>' + products[key].price  +'</td>' +
				  '<td>' + '<input class=quantity type=text id='+ products[key].id + '>'+ '</td>'+
				  '</tr>';
		
		table_data += row;
	}
   $('#products_table>tbody').append(table_data);
}

window.cartCheckout = function(){
	var arrayOfQuantities = $('.quantity');
	var checkoutRequest = [];
	
	for(var index= 0 ; index < window.data.length; index++ ){
		if($(arrayOfQuantities[index])){
			var quant = $(arrayOfQuantities[index]).val();
			if(quant !== '' && quant !== 0 && quant > 0){
				var id = $(arrayOfQuantities[index]).attr('id');
				var cartItem = {
						id : id,
						quantity : quant
				};
				checkoutRequest.push(cartItem);
			}
		}
	}
	
	var request = {cart : checkoutRequest };
	
	window.callService("/products/checkout", handleCheckoutResponse, request);
	
}

window.handleCheckoutResponse = function(responseData){
    
	var billItems = responseData.billItems;
	var table_data = '';
	
	for(var key=0; key <= billItems.length; key++ ){
		if(billItems[key]){
			var row = '<tr>' +
			  '<td>' + billItems[key].id  +'</td>' +
			  '<td>' + billItems[key].quantity  +'</td>' +
			  '<td>' + billItems[key].salesTaxAmt + ' + ' + billItems[key].priceWithoutTax +'</td>' +
			  '<td>' + billItems[key].total  +'</td>' +
			  '</tr>';
			
			table_data += row;
		}
	}
    
	$('#bill_table>tbody').append(table_data);
	$('#numberOfItems').text(responseData.totalItems);
	$('#totalTax').text(responseData.totalSalesTax);
	$('#totalBill').text(responseData.totalBillAmt);
	
	$('#billDetails').css("display", "inline");
	$('#orderSection').css("display", "none");
	
}

window.refresh = function(){
	location.reload();
}