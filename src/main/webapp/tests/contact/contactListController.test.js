(function (){
    'use strict';

    describe('contactListController', function (){
        var contactListController;
        var mockBackend;
        var toastrMessages;

        beforeEach(angular.mock.module('avaliacandidatos'));

        beforeEach(angular.mock.inject(function ($controller, $httpBackend, toastr){
            contactListController = $controller('contactListController');
            mockBackend = $httpBackend;
            toastrMessages = toastr;
        }));

        it('Não deve excluir um contato se não foi pré-definido.', function (){
            spyOn(toastrMessages, 'success');
            contactListController.delete();
            expect(toastrMessages.success).not.toHaveBeenCalled();
        });

        it('Deve excluir o contato selecionado.', function (){
            spyOn(toastrMessages, 'success');
            mockBackend.expectDELETE('/contacts').respond(200);
            mockBackend.expectGET('/contacts').respond(200);
            contactListController.preDelete({});
            contactListController.delete();
            mockBackend.flush();
            expect(toastrMessages.success).toHaveBeenCalledWith('Registro deletado com sucesso.', 'Sucesso!');
        });

        it('Deve exibir uma mensagem quando ocorrer erro ao excluir.', function (){
            spyOn(toastrMessages, 'error');
            mockBackend.expectDELETE('/contacts').respond(500);
            contactListController.preDelete({});
            contactListController.delete();
            mockBackend.flush();
            expect(toastrMessages.error).toHaveBeenCalledWith(undefined, 'Ops, algo errado aconteceu.');
        });
    });
})();