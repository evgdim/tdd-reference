package com.github.evgdim.tdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.evgdim.tdd.entity.Person;
import com.github.evgdim.tdd.repository.PersonRepository;
import com.github.evgdim.tdd.service.AccountClient;
import com.github.evgdim.tdd.service.PersonNotOldEnoughException;
import com.github.evgdim.tdd.service.PersonServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class O3_MockitoTests {
	@Mock private PersonRepository personRepository;
	@Mock private AccountClient accountClient;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void checkPerson_shouldReturnIt_whenItsOver18() {
		//given
		when(personRepository.findById(1L)).thenReturn(Optional.of(kiroAge20()));
		
		//when
		PersonServiceImpl personServ= new PersonServiceImpl(personRepository, accountClient);
		Person person = personServ.checkPerson(1L);
		
		//should
		assertThat(person.getName()).isNotBlank();
	}
	
	@Test
	public void checkPerson_shouldThrowPersonNotOldEnoughException_whenItsUnder18() {
		//given
		when(personRepository.findById(1L)).thenReturn(Optional.of(atanasAge15()));
		
		//should
		this.thrown.expect(PersonNotOldEnoughException.class);
		
		//when
		PersonServiceImpl personServ= new PersonServiceImpl(personRepository, accountClient);
		personServ.checkPerson(1L);
		
	}
	
	@Test
	public void accountClient_shouldBeCalled_whenPersonExists() {
		//given
		when(personRepository.findById(1L)).thenReturn(Optional.of(kiroAge20()));
		
		//when
		PersonServiceImpl personServ= new PersonServiceImpl(personRepository, accountClient);
		personServ.checkPerson(1L);
		
		
		//should
		verify(accountClient, times(1)).findAccounts(Mockito.anyLong());
		verify(accountClient, times(1)).findAccounts(1L);
	}
	
	@Test
	public void accountClient_shouldBeCalledForThePersonId_whenPersonExists() {
		//given
		when(personRepository.findById(1L)).thenReturn(Optional.of(kiroAge20()));
		
		//when
		PersonServiceImpl personServ= new PersonServiceImpl(personRepository, accountClient);
		personServ.checkPerson(1L);
		
		ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
		verify(accountClient).findAccounts(argumentCaptor.capture());
		
		Long actualtAccountsArgument = argumentCaptor.getValue();
		assertThat(actualtAccountsArgument).isEqualByComparingTo(1L);
	}

	private Person kiroAge20() {
		return new Person(1L, "Kiro", 20);
	}
	
	private Person atanasAge15() {
		return new Person(1L, "Atanas", 15);
	}
}
