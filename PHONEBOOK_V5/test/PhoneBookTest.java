import gabor.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import throwables.InvalidEmailException;
import throwables.InvalidPhoneNumberException;

import javax.management.InstanceAlreadyExistsException;
import javax.naming.SizeLimitExceededException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PhoneBookTest
{


	private PhoneBook phoneBook;


	@Before
	public void create()
	{
		phoneBook = new PhoneBook(3, "TEST");
	}


	@Test
	public void getNumPhoneBooks()
	{
		int old = PhoneBook.getNumPhoneBooks();
		new PhoneBook();
		assertEquals(old + 1, PhoneBook.getNumPhoneBooks());

	}

	@Test
	public void add() throws InvalidEmailException, InvalidPhoneNumberException
	{

		String n = TestUtils.randomString(10);
		Contact c = new Contact(n);
		try
		{
			phoneBook.add(c);
		} catch (InstanceAlreadyExistsException | SizeLimitExceededException e)
		{
			fail();
		}
		try
		{
			phoneBook.add(c);
			fail();
		} catch (InstanceAlreadyExistsException ignored)
		{
		} catch (SizeLimitExceededException e)
		{
			fail(e.getMessage());
		}
		for (int i = phoneBook.size(); i < phoneBook.MAX_SIZE; i++)
		{
			n += n;
			try
			{
				phoneBook.add(new Contact(n));
			} catch (InstanceAlreadyExistsException | SizeLimitExceededException e)
			{
				fail(e.getMessage());
			}
		}
		n += n;
		try
		{
			phoneBook.add(n);
			fail();
		} catch (InstanceAlreadyExistsException e)
		{
			fail(e.getMessage());
		} catch (SizeLimitExceededException ignored)
		{
		}
	}

	@Test
	public void add1()
	{
		String n = TestUtils.randomString(10);
		try
		{
			phoneBook.add(n);
		} catch (InstanceAlreadyExistsException | SizeLimitExceededException | InvalidPhoneNumberException | InvalidEmailException e)
		{
			fail(e.getMessage());
		}
		try
		{
			phoneBook.add(n);
			fail();
		} catch (InstanceAlreadyExistsException ignored)
		{
		} catch (SizeLimitExceededException | InvalidPhoneNumberException | InvalidEmailException e)
		{
			fail(e.getMessage());
		}
		for (int i = phoneBook.size(); i < phoneBook.MAX_SIZE; i++)
		{
			n += n;
			try
			{
				phoneBook.add(n);
			} catch (InstanceAlreadyExistsException | SizeLimitExceededException | InvalidPhoneNumberException | InvalidEmailException e)
			{
				fail(e.getMessage());
			}
		}
		n += n;
		try
		{
			phoneBook.add(n);
			fail();
		} catch (InstanceAlreadyExistsException | InvalidPhoneNumberException | InvalidEmailException e)
		{
			fail(e.getMessage());
		} catch (SizeLimitExceededException ignored)
		{
		}

	}

	@Test
	public void add2()
	{
		String n = TestUtils.randomString(10);
		String email = n + "@gmail.com";
		try
		{
			phoneBook.add(n, email);
		} catch (InstanceAlreadyExistsException | SizeLimitExceededException | InvalidPhoneNumberException | InvalidEmailException e)
		{
			fail(e.getMessage());
		}
		try
		{
			phoneBook.add(n, email);
			fail();
		} catch (InstanceAlreadyExistsException ignored)
		{
		} catch (SizeLimitExceededException | InvalidPhoneNumberException | InvalidEmailException e)
		{
			fail(e.getMessage());
		}
		for (int i = phoneBook.size(); i < phoneBook.MAX_SIZE; i++)
		{
			n += n;
			try
			{
				phoneBook.add(n, email);
			} catch (InstanceAlreadyExistsException | SizeLimitExceededException | InvalidPhoneNumberException | InvalidEmailException e)
			{
				fail(e.getMessage());
			}
		}
		n += n;
		try
		{
			phoneBook.add(n, email);
			fail();
		} catch (InstanceAlreadyExistsException | InvalidPhoneNumberException | InvalidEmailException e)
		{
			fail(e.getMessage());
		} catch (SizeLimitExceededException ignored)
		{
		}

	}

	@Test
	public void add3()
	{
		String n = TestUtils.randomString(10);
		String email = n + "@gmail.com";
		String phones = "123,234,345,456";

		try
		{
			phoneBook.add(n, email, phones);
		} catch (InstanceAlreadyExistsException | SizeLimitExceededException | InvalidPhoneNumberException | InvalidEmailException e)
		{
			fail(e.getMessage());
		}
		try
		{
			phoneBook.add(n, email, phones);
			fail();
		} catch (InstanceAlreadyExistsException ignored)
		{
		} catch (SizeLimitExceededException | InvalidPhoneNumberException | InvalidEmailException e)
		{
			fail(e.getMessage());
		}
		for (int i = phoneBook.size(); i < phoneBook.MAX_SIZE; i++)
		{
			n += n;
			try
			{
				phoneBook.add(n, email, phones);
			} catch (InstanceAlreadyExistsException | SizeLimitExceededException | InvalidPhoneNumberException | InvalidEmailException e)
			{
				fail(e.getMessage());
			}
		}
		n += n;
		try
		{
			phoneBook.add(n, email, phones);
			fail();
		} catch (InstanceAlreadyExistsException | InvalidPhoneNumberException | InvalidEmailException e)
		{
			fail(e.getMessage());
		} catch (SizeLimitExceededException ignored)
		{
		}
	}

	@Test
	public void findByName() throws SizeLimitExceededException, InvalidEmailException, InvalidPhoneNumberException, InstanceAlreadyExistsException
	{
		String n = TestUtils.randomString(10);
		String n1 = TestUtils.randomString(10);
		String n2 = TestUtils.randomString(10);
		phoneBook.add(n);
		phoneBook.add(n1);
		phoneBook.add(n2);
		List<Contact> l = phoneBook.findByName(n.substring(0, TestUtils.randomInteger(1, n.length() - 1)));
		assertFalse(l.isEmpty());
		assertEquals(1, l.size());
		assertEquals(n, l.get(0).name);
	}

	@Test
	public void findByEmail() throws SizeLimitExceededException, InvalidEmailException, InvalidPhoneNumberException, InstanceAlreadyExistsException
	{
		String n = TestUtils.randomString(10);
		String e = n + "@gmail.com";
		String n1 = TestUtils.randomString(10);
		String e1 = n + "@gmail.com";
		String n2 = TestUtils.randomString(10);
		String e2 = n2 + "@gmail.com";
		phoneBook.add(n, e);
		phoneBook.add(n1, e1);
		phoneBook.add(n2, e2);
		List<Contact> l = phoneBook.findByEmail(e.substring(0, TestUtils.randomInteger(1, n.length() - 1)));
		assertFalse(l.isEmpty());
		assertEquals(2, l.size());
		assertEquals(n, l.get(0).name);
	}

	@Test
	public void deleteByName() throws SizeLimitExceededException, InvalidEmailException, InvalidPhoneNumberException, InstanceAlreadyExistsException
	{
		String n = TestUtils.randomString(10);
		phoneBook.add(n);
		List<Contact> l = phoneBook.findByName(n);
		assertFalse(l.isEmpty());
		assertEquals(1, l.size());
		assertTrue(phoneBook.deleteByName(n));
		l = phoneBook.findByName(n);
		assertTrue(l.isEmpty());
		assertFalse(phoneBook.deleteByName(n));


	}

	@Test
	public void deleteByEmail() throws SizeLimitExceededException, InvalidEmailException, InvalidPhoneNumberException, InstanceAlreadyExistsException
	{
		String n = TestUtils.randomString(10);
		String e = n + "@gmail.com";
		String n1 = TestUtils.randomString(10);
		String e1 = n + "@gmail.com";
		String n2 = TestUtils.randomString(10);
		String e2 = n2 + "@gmail.com";
		phoneBook.add(n, e);
		phoneBook.add(n1, e1);
		phoneBook.add(n2, e2);
		List<Contact> l = phoneBook.findByEmail(e);
		assertFalse(l.isEmpty());
		assertEquals(2, l.size());
		assertTrue(phoneBook.deleteByEmail(e));
		l = phoneBook.findByEmail(e);
		assertTrue(l.isEmpty());
		assertFalse(phoneBook.deleteByEmail(e));
	}

	@Test
	public void size()
	{
		int old = PhoneBook.getNumPhoneBooks();
		new PhoneBook();
		assertEquals(old + 1, PhoneBook.getNumPhoneBooks());
	}

	@Test
	public void getName()
	{
		String n = TestUtils.randomString(10);
		PhoneBook p = new PhoneBook(1, n);
		assertEquals(n, p.getName());
		p = new PhoneBook();
		assertEquals("Phonebook " + (PhoneBook.getNumPhoneBooks() - 1), p.getName());
	}

	@Test
	public void multiPhoneBook() throws InvalidEmailException, InvalidPhoneNumberException, InstanceAlreadyExistsException, SizeLimitExceededException
	{
		String n = TestUtils.randomString(10);
		PhoneBook p = new PhoneBook();

		Contact c = new Contact(n);
		phoneBook.add(c);
		p.add(c);

		assertEquals(phoneBook.findByName(c.name).get(0).getEmail(), p.findByName(c.name).get(0).getEmail());
		c.setEmail(n + "@live.it");
		assertEquals(phoneBook.findByName(c.name).get(0).getEmail(), p.findByName(c.name).get(0).getEmail());
		assertEquals(c.getEmail(), phoneBook.findByName(c.name).get(0).getEmail());
		assertEquals(c.getEmail(), p.findByName(c.name).get(0).getEmail());
	}

	@Test
	public void ioTest(){
		String filename = new SimpleDateFormat("dd-MM-yyyy").format(new Date())+".csv";
		String name = TestUtils.randomString(10);
		String email = name+"@mail.com";
		StringBuilder phones = new StringBuilder();
		for(int i = 0; i< 5; i++){
			phones.append(TestUtils.randomInteger(300000000, 340000000)).append(",");
		}
		phones.append(TestUtils.randomInteger(300000000, 340000000));
		try {
			phoneBook.add(name,email, phones.toString());
		} catch (InvalidEmailException | InvalidPhoneNumberException | InstanceAlreadyExistsException | SizeLimitExceededException e) {
			fail();
		}
		try {
			phoneBook.saveToFile(filename);
		} catch (IOException e) {
			fail();
		}
		assertTrue(Files.exists(Paths.get(filename)));
		phoneBook.deleteByName(name);
		try {
			phoneBook.addFromFile(filename);
		} catch (IOException | SizeLimitExceededException | InvalidEmailException | InvalidPhoneNumberException | InstanceAlreadyExistsException e) {
			fail();
		}
		assertTrue(phoneBook.findByName(name).size()>0);
	}

	@Test
	public void hasDoubleEmailsAndIterator() throws SizeLimitExceededException, InvalidEmailException, InvalidPhoneNumberException, InstanceAlreadyExistsException {
		String name = TestUtils.randomString(10);
		phoneBook.add(name);
		assertFalse(PhoneBook.hasDoubleEmails(phoneBook));
		phoneBook.add(name+name);
		assertTrue(PhoneBook.hasDoubleEmails(phoneBook));
	}
}