(function (){
    'use strict';

    describe('contactAddEditController', function (){

        var contactAddEditController;
        var mockBackend;
        var toastrMessages;

        beforeEach(angular.mock.module('avaliacandidatos'));

        beforeEach(angular.mock.inject(function ($controller, $httpBackend, toastr, $rootScope, $compile){
            contactAddEditController = $controller('contactAddEditController');
            mockBackend = $httpBackend;
            toastrMessages = toastr;
            var scope = $rootScope.$new();
            var ele = angular.element('<div id="myModal"></div>');
            $compile(ele)(scope);
            scope.$apply();
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

        it('Deve exibir uma mensagem quando ocorrer erro ao salvar.', function (){
            spyOn(toastrMessages, 'error');
            mockBackend.expectPOST('/contacts').respond(500);
            contactAddEditController.contact.name = "Vinicius";
            contactAddEditController.save();
            mockBackend.flush();
            expect(toastrMessages.error).toHaveBeenCalledWith(undefined, 'Ops, algo errado aconteceu.');
        });
    });
})();