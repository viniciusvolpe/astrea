(function (){
    'use strict';

    describe('contactAddEditController', function (){

        var contactAddEditController;
        var mockBackend;
        var toastrMessages;

        beforeEach(angular.mock.module('avaliacandidatos'));

        beforeEach(angular.mock.inject(function ($controller, $httpBackend, toastr){
            contactAddEditController = $controller('contactAddEditController');
            mockBackend = $httpBackend;
            toastrMessages = toastr;
        }));

        it('Não deve salvar contatos sem nome.', function (){
            spyOn(toastrMessages, 'warning');
            contactAddEditController.save();
            expect(toastrMessages.warning).toHaveBeenCalledWith('O campo nome deve ser preenchido.', 'Aviso!');
        });

        it('Deve salvar um contato se os dados estiverem corretamente preenchidos.', function(){
            spyOn(toastrMessages, 'success');
            mockBackend.expectPOST('/contacts').respond(200);
            contactAddEditController.contact.name = "Vinicius";
            contactAddEditController.save();
            mockBackend.flush();
            expect(toastrMessages.success).toHaveBeenCalledWith('Seu contato foi salvos.', 'Sucesso!');
        });
    });
})();