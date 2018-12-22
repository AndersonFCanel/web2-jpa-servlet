/*$(function() {

    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

});*/

//Filtro de Busca
$(function() {
	$("#txtBusca").keyup(function() {
		var texto = $(this).val();
		console.log(texto)
		$("#ulItens li").css("display", "block");
		$("#ulItens li").each(function() {
			// Modificação no filtro para torná-lo case insensitivo
			if ($(this).text().toUpperCase().indexOf(texto.toUpperCase()) < 0)
				$(this).css("display", "none");
		});
	});
});

// Quando um checkbox da tela de listagem de assentos for selecionado
window.onload = function() {
	if (document.getElementById('numeroAssento').checked) {
		$("#ocupa").show();
	} else {
		$("#ocupa").hide();
	}

	if (document.getElementById('numeroAssentoOcupado').checked) {
		$("#desocupa").show();
	} else {
		$("#desocupa").hide();
	}
}
// $('#numeroAssentoOcupado').click(function() {
// $("#ocupa").toggle(this.checked);
// });

// Escondendo voo de volta ao carregar a página
window.onload = function() {
	$("#ulVolta").hide();
}

// Lista de voo de volta
function uncheckVooVolta() {
	document.getElementById("vooDeVolta").checked = false
	// document.getElementsByName("voltaId").checked = false
	var x = document.getElementsByName("voltaId");
	// console.log(document.getElementById("vooDeVolta"))
	// console.log(document.getElementsByName("voltaId"))
	var i;
	for (i = 0; i < x.length; i++) {
		if (x[i].type == "radio") {
			console.log(x[i])
			x[i].checked = false;
		}
	}
	$(".liVolta").css("background", "");
}

$(document).ready(function() {
	$('input[type=radio]').click(function() {
		// alert(this.value)
		console.log(this.value)
		if (this.value == 'on') {
			uncheckVooVolta();
			// Esconde
			// $("input[type=radio]").click(function() {
			$("#ulVolta").hide();
			// });
		}
		if (this.value == '1') {
			// Mostra
			// $("input[type=radio]").click(function() {
			$("#ulVolta").show();
			// });
		}
	});
});

// Marcando linha selecionada
$(document).ready(function() {
	// $('input[type=radio]').click(function() {
	$('#ulItens').find('input[type=radio]').click(function() {
		var id = $(".liIdaImput" + this.value).val();
		console.log("Ida***" + id);
		$(".liIda").css("background", "");
		$(".liVolta").show();
		$(".espacador").show();
		//$(".separador").show();
		if (this.value > 0 && id == this.value) {
			// Muda a cor do fudo quando for marcado
			// A função muda o background da div com id="#liSelect"
			$("#liSelect" + this.value).css("background", "#BDB76B");
			$("#liSelect" + this.value + "Volta").css("background", "");
			//$(".liVoltaImput" + this.value).checked = false;
			$(".liVoltaImput"+ this.value).prop("checked", false);
			$("#liSelect" + this.value + "Volta").hide();
			$("#liLinha" + this.value + "Volta").hide();
		}
	});
});

$(document).ready(function() {
	// $('input[type=radio]').click(function() {
	$('#ulVolta').find('input[type=radio]').click(function() {
		var id = $(".liVoltaImput" + this.value).val();
		console.log("Volta***" + id);
		$(".liVolta").css("background", "");
		if (this.value > 0 && id == this.value) {
			// Muda a cor do fudo quando for marcado
			// A função muda o background da div com id="#liSelect"
			$("#liSelect" + this.value + "Volta").css("background", "#BDB76B");
		}
	});
});

//Escondendo voo de ida SELECIONADO na lista dos voos de volta
/*$(document).ready(function() {
 // $('input[type=radio]').click(function() {
 $('#ulItens').find('input[type=radio]').click(function() {
 var id = $(".liIdaImput" + this.value).val();
 $(".liIda").css("background", "");
 if (this.value > 0 && id == this.value) {
 console.log("Voo Ida selecionado: " + id);
 $("#liSelect" + this.value + "Volta").hide();
 }
 });
 });*/

//Desabilitar duplo clique
var formID = document.getElementById("formID");
var send = $("#send");

$(formID).submit(function(event){
  if (formID.checkValidity()) {
    send.attr('disabled', 'disabled');
  }
});
//=======>>USAR DESTA FORMA NO HTML
/*
 * <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<form id="formID">
  <input type="text" required />
  <input type="submit" class="formButton" name="send" id="send" value="Enviar">
</form>
 * */

//Desabilitar duplo envio
$('form').submit(function() {
    if(typeof jQuery.data(this, "disabledOnSubmit") == 'undefined') {
        jQuery.data(this, "disabledOnSubmit", { submited: true });
        $('input[type=submit], input[type=button]', this).each(function() {
            $(this).attr("disabled", "disabled");
      });
      //$('input[type=submit], input[type=button]', this).attr("disabled", "disabled");
      return true;
}
else
{
    return false;
}
});

//Validação de campos em formulários




