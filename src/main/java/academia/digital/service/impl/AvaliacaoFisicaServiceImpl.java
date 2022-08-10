package academia.digital.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academia.digital.entity.Aluno;
import academia.digital.entity.AvaliacaoFisica;
import academia.digital.entity.form.AvaliacaoFisicaForm;
import academia.digital.repository.AlunoRepository;
import academia.digital.repository.AvaliacaoFisicaRepository;
import academia.digital.service.IAvaliacaoFisicaService;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

  @Autowired
  private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

  @Autowired
  private AlunoRepository alunoRepository;

  @Override
  public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
    AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
    Aluno aluno = alunoRepository.findById(form.getAlunoId()).get();

    avaliacaoFisica.setAluno(aluno);
    avaliacaoFisica.setPeso(form.getPeso());
    avaliacaoFisica.setAltura(form.getAltura());

    return avaliacaoFisicaRepository.save(avaliacaoFisica);
  }

  @Override
  public AvaliacaoFisica get(Long id) {
    return null;
  }

  @Override
  public List<AvaliacaoFisica> getAll() {

    return avaliacaoFisicaRepository.findAll();
  }

  /*@Override
  public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
    return null;
  }*/
  
  @Override
  public Optional<AvaliacaoFisica> update(AvaliacaoFisica avaliacaoFisica) {
		
		if(avaliacaoFisicaRepository.findById(avaliacaoFisica.getId()).isPresent()) {		
									
			return Optional.ofNullable(avaliacaoFisicaRepository.save(avaliacaoFisica));			
		}	
		return Optional.empty();	
  }	


  @Override
  public void delete(Long id) {
	  avaliacaoFisicaRepository.deleteById(id);
  }

}
