package academia.digital.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academia.digital.entity.Aluno;
import academia.digital.entity.AvaliacaoFisica;
import academia.digital.entity.form.AlunoForm;
import academia.digital.infra.utils.JavaTimeUtils;
import academia.digital.repository.AlunoRepository;
import academia.digital.service.IAlunoService;

@Service
public class AlunoServiceImpl implements IAlunoService {

  @Autowired
  private AlunoRepository repository;

  @Override
  public Aluno create(AlunoForm form) {
    Aluno aluno = new Aluno();
    aluno.setNome(form.getNome());
    aluno.setCpf(form.getCpf());
    aluno.setBairro(form.getBairro());
    aluno.setDataDeNascimento(form.getDataDeNascimento());

    return repository.save(aluno);
  }

  @Override
  public Aluno get(Long id) {
    return null;
  }

  @Override
  public List<Aluno> getAll(String dataDeNascimento) {

    if(dataDeNascimento == null) {
      return repository.findAll();
    } else {
      LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
      return repository.findByDataDeNascimento(localDate);
    }

  }

    
  @Override
  public Optional<Aluno> update(Aluno aluno) {
		
		if(repository.findById(aluno.getId()).isPresent()) {		
									
			return Optional.ofNullable(repository.save(aluno));			
		}	
		return Optional.empty();	
	}	

  @Override
  public void delete(Long id) {
	  repository.deleteById(id);
  }

  
 
  @Override
  public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id) {

    Aluno aluno = repository.findById(id).get();

    return aluno.getAvaliacoes();

  }


}
