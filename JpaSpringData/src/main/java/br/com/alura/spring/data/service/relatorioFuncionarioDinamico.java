package br.com.alura.spring.data.service;

import br.com.alura.spring.data.Specification.SpecificationFuncionario;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class relatorioFuncionarioDinamico {

    private final FuncionarioRepository funcionarioRepository;

    private  final DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public relatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }



    public  void inicial(Scanner scanner){

        System.out.println("Digite o nome:");
        String nome = scanner.next();
        if (nome.equalsIgnoreCase("NULL")){
            nome = null;
        }

        System.out.println("Digite o cpf:");
        String cpf = scanner.next();
        if (nome.equalsIgnoreCase("NULL")){
            cpf = null;
        }

        System.out.println("Digite o salario:");
        Double salario = scanner.nextDouble();
        if (salario == 0){
            cpf = null;
        }

        System.out.println("Digite a data de contratação :");
        String data = scanner.next();
        LocalDate dataContratacao = null;
        if (data.equalsIgnoreCase("NULL")){
            cpf = null;
        }else{
            dataContratacao = LocalDate.parse(data, formatter);
        }

        List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification.where(
        SpecificationFuncionario.nome(nome)
        .or(SpecificationFuncionario.cpf(cpf))
        .or(SpecificationFuncionario.salario(salario))
        .or(SpecificationFuncionario.dataContratacao(dataContratacao))));

        funcionarios.forEach(System.out::println);
    }



}
