(function (){
    'use strict';

    describe('bdayFilter', function (){
        var bday;
        beforeEach(angular.mock.module('avaliacandidatos'));

        beforeEach(angular.mock.inject(function ($filter){
            bday = $filter('bday');
        }));

        it('Deve formatar a data de nascimento quando informar um objeto.', function (){
            var contact = {
                birthDay: 1,
                birthMonth: 1,
                birthYear: 1990
            }
            var result = bday(contact);
            expect(result).toEqual('1/1/1990');
        });

        it('Deve retornar undefined se o objeto não for informado.', function (){
            var result = bday(null);
            expect(result).toBeUndefined();
        });

        it('Deve retornar "Incompleto" quando um dos dados não foi informado.', function (){
            var contact = {
                birthDay: 1,
                birthMonth: 1,
                birthYear: null
            }
            var result = bday(contact);
            expect(result).toEqual('Incompleto');
        });
    });
})();