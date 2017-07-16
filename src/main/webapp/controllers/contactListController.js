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
				if(!vm.preDeletedContact) return;
				contactService.delete(vm.preDeletedContact).then(function (){
					toastr.success('Registro deletado com sucesso.', 'Sucesso!');
					vm.preDeletedContact = undefined;
					_listAllContacts();
					$('#myModal').modal('hide');
				}).catch(function (error){
					toastr.error(error.message, 'Ops, algo errado aconteceu.');
				});
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

