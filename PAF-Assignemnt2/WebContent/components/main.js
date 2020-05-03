$(document).ready(function() {  
	
	if ($("#alertSuccess").text().trim() == "")  {   
		$("#alertSuccess").hide();  
		
	}  
	$("#alertError").hide(); 
	
});

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) {  
	
	// Clear status msges-------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation----------------  
	var status = validateItemForm(); 

	// If not valid-------------------  
	if (status != true)  {   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid-----------------------  
	$("#formAppointment").submit(); 
	
	$("#alertSuccess").text("Inserted successfully.");  
	$("#alertSuccess").show(); 
	
}); 

//INSERT ============================================ 
function validateItemForm() {  
	
	// NAME  
	if ($("#name").val().trim() == "")  {   
		return "Insert fullName.";  
		
	} 
	
	 // MOBILE  
	if ($("#mobile").val().trim() == "")  {   
		return "Insert Mobile.";  
		
	} 
	 
	 // is numerical value  
	var tmpMobile = $("#mobile").val().trim();  
	if (!$.isNumeric(tmpMobile))  {   
		return "Insert a numerical value for Mobile Number.";  
		
	}
	 
	 // Email 
	if ($("#email").val().trim() == "")  {   
		return "Insert Email.";  
		
	} 
	
	// NIC  
	if ($("#nic").val().trim() == "")  {   
		return "Insert NIC.";  
		
	} 
	 
	 
	
	// Address  
	if ($("#address").val().trim() == "")  {   
		return "Insert address.";  
		
	} 
	
	// Date  
	if ($("#date").val().trim() == "")  {   
		return "Insert date.";  
		
	} 
	
	// HOSPITALname  
	if ($("#hospital").val().trim() == "")  {   
		return "Insert Hospital Name.";  
		
	} 
	
	// DocName  
	if ($("#doctor").val().trim() == "")  {   
		return "Insert Doctor Name.";  
		
	} 
	
	// Msg 
	if ($("#msg").val().trim() == "")  {   
		return "Insert Message.";  
		
	} 
	 
	 return true; 
	 
}


//REMOVE========================================== 
$(document).on("click", ".btnRemove", function(event) {  
	
	$(this).closest("tr").remove();    
	$("#alertSuccess").text("Removed successfully.");  
	$("#alertSuccess").show(); 
	
});

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) {     
	
	$("#hidappIDSave").val($(this).closest("tr").find('#hidappIDUpdate').val());     
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#mobile").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#email").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#nic").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#address").val($(this).closest("tr").find('td:eq(4)').text()); 
	$("#date").val($(this).closest("tr").find('td:eq(5)').text()); 
	$("#hospital").val($(this).closest("tr").find('td:eq(6)').text()); 
	$("#doctor").val($(this).closest("tr").find('td:eq(7)').text()); 
	$("#msg").val($(this).closest("tr").find('td:eq(8)').text()); 
	
	$("#alertSuccess").text("Updated successfully.");  
	$("#alertSuccess").show(); 
	
});
