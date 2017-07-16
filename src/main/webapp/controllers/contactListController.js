(function (){
	'use strict';
	angular
		.module('avaliacandidatos')
		.controller("contactListController", ContactListController);

		ContactListController.$inject = ['ContactService', 'toastr'];

		function ContactListController(contactService, toastr){
			var vm = this;
			vm.contacts = [];
			vm.preDeletedContact = {};

			vm.init = _init;
			vm.listAllContacts = _listAllContacts;
			vm.preDelete = _preDelete;
			vm.delete = _delete;
			vm.bday = _bday;

			function _init() {
				vm.listAllContacts();
			};
			
			function _listAllContacts() {
				contactService.get().then(function (response){
					vm.contacts = response;
				}).catch(function (error){
					toastr.error(error.message, 'Ops, algo errado aconteceu.');
				});
			};

			function _preDelete(contact) {
				vm.preDeletedContact = contact;
				$('#myModal').modal('show');
			};

			function _delete() {
				if(vm.preDeletedContact != null) {

					// Chamar o servlet /contacts com um método 'DELETE' para deletar um contato do banco de dados passando um parâmetro de identificação.
				}
			};

			function _bday(c) {
				if(c.birthDay==null || c.birthDay == ""){
					return "";
				} else {
					return c.birthDay + "/" + c.birthMonth + "/" + c.birthYear;
				}
			};
		}
	
})();

