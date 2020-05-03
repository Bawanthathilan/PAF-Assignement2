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
		return "Insert Name.";  
		
	} 
	 
	 // NIC  
	if ($("#nic").val().trim() == "")  {   
		return "Insert NIC.";  
		
	} 
	
	// ADDRESS  
	if ($("#address").val().trim() == "")  {   
		return "Insert Address.";  
		
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
	
	// EMAIL  
	if ($("#email").val().trim() == "")  {   
		return "Insert Email.";  
		
	} 
	
	// SPECIALIZATION  
	if ($("#spec").val().trim() == "")  {   
		return "Insert Specialization.";  
		
	} 
	
	// HOSPITAL  
	if ($("#hospital").val().trim() == "")  {   
		return "Insert Hospital Name.";  
		
	} 
	
	// DEPARTMENT  
	if ($("#dept").val().trim() == "")  {   
		return "Insert Department Name.";  
		
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
	
	$("#hidDoctorIDSave").val($(this).closest("tr").find('#hidDoctorIDUpdate').val());     
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#nic").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#address").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#mobile").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#email").val($(this).closest("tr").find('td:eq(4)').text()); 
	$("#spec").val($(this).closest("tr").find('td:eq(5)').text()); 
	$("#hospital").val($(this).closest("tr").find('td:eq(6)').text()); 
	$("#dept").val($(this).closest("tr").find('td:eq(7)').text()); 
	
	$("#alertSuccess").text("Updated successfully.");  
	$("#alertSuccess").show(); 
	
});
