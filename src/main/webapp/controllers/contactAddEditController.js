(function (){
	'use strict';

	angular
		.module('avaliacandidatos')
		.controller('contactAddEditController', ContactAddEditController)

		ContactAddEditController.$inject = ['ContactService', 'toastr'];

		function ContactAddEditController(contactService, toastr){
			var vm = this;

			vm.contact = {};
			vm.contact.emails = [''];
			vm.contact.phones = [''];
			vm.submitted = false;
			
			// Exposed
			vm.save = _save;
			vm.addMorePhones = _addMorePhones;
			vm.addMoreEmails = _addMoreEmails;
			vm.deletePhone = _deletePhone;
			vm.deleteEmail = _deleteEmail;

			function _save() {
				vm.submitted = true;
				if (!vm.contact.name) return toastr.warning('O campo nome deve ser preenchido.', 'Aviso!');
				contactService.save(vm.contact).then(function (){
					toastr.success('Seu contato foi salvos.', 'Sucesso!');
				}).catch(function (error){
					toastr.error(error.message, 'Ops, algo errado aconteceu.');
				});
			};

			function _addMorePhones() {
				vm.contact.phones.push('');
			}; 

			function _addMoreEmails() {
				vm.contact.emails.push('');
			};

			function _deletePhone(index){
				if (index > -1) {
					vm.contact.phones.splice(index, 1);
				}

				if (vm.contact.phones.length < 1){
					vm.addMorePhones();
				}
			};

			function _deleteEmail(index){
				if (index > -1) {
					vm.contact.emails.splice(index, 1);
				}

				if (vm.contact.emails.length < 1){
					vm.addMoreEmails();
				}
			};
		}
})();