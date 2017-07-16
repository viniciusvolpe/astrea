(function (){
	'use strict';
	angular
		.module('avaliacandidatos')
		.controller("contactListController", ContactListController);

		ContactListController.$inject = ['ContactService', 'toastr', '$state'];

		function ContactListController(contactService, toastr, $state){
			var vm = this;
			vm.contacts = [];
			vm.preDeletedContact = {};

			vm.init = _init;
			vm.listAllContacts = _listAllContacts;
			vm.preDelete = _preDelete;
			vm.delete = _delete;
			vm.edit = _edit;
			vm.findByFilter = _findByFilter;
			vm.clear = _clear;

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

			function _edit(contact){
				$state.go('main.addeditcontact', {id: contact.id});
			}

			function _findByFilter(){
				contactService.findByFilter(vm.filter).then(function (response){
					vm.contacts = response;
				}).catch(function (error){
					toastr.error(error.message, 'Ops, algo errado aconteceu.');
				});
			}

			function _clear(){
				vm.filter = undefined;
				_listAllContacts();
			}
		}
	
})();

