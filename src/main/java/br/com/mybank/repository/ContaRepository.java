package br.com.mybank.repository;

import br.com.mybank.model.Conta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContaRepository extends MongoRepository<Conta, String> {


    List<Conta> processado(boolean processado);

    @Query("{$and: [ {'saldo': {$gte: ?0}}, {'saldo': {$lte: ?1} } ]}")
    List<Conta> obterContasPorSaldo(Double de, Double ate);


}
