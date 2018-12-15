import gabor.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import throwables.InvalidEmailException;
import throwables.InvalidPhoneNumberException;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.naming.SizeLimitExceededException;

import static org.junit.Assert.*;

public class PhoneBookManagerTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        PhoneBookManager.init();
    }

    @After
    public void tearDown() throws Exception {
        PhoneBookManager.reset();
    }

    @Test
    public void create() {
        String name = TestUtils.randomString(10);
		try
		{
			PhoneBookManager.size(name);
			fail();
		} catch (InstanceNotFoundException ignored)
		{}
		try
		{
			PhoneBookManager.create(name, TestUtils.randomInteger(1,5));
		} catch (InstanceAlreadyExistsException e)
		{
			fail(e.getMessage());
		}
		try
		{
			PhoneBookManager.create(name, TestUtils.randomInteger(1,5));
			fail();
		} catch (InstanceAlreadyExistsException ignored)
		{}
		try
		{
			assertEquals(0, PhoneBookManager.size(name));
		} catch (InstanceNotFoundException e)
		{
			fail(e.getMessage());
		}

	}

    @Test
    public void delete() throws InstanceAlreadyExistsException, InstanceNotFoundException
	{
        String name = TestUtils.randomString(10);
        PhoneBookManager.create(name, TestUtils.randomInteger(1,5));
        PhoneBookManager.delete(name);
		exception.expect(InstanceNotFoundException.class);
		PhoneBookManager.size(name);
    }

    @Test
    public void add() throws InstanceAlreadyExistsException, SizeLimitExceededException, InvalidEmailException, InvalidPhoneNumberException, InstanceNotFoundException
	{
        String name = TestUtils.randomString(10);
        String notName = name+TestUtils.randomString(5);
        Contact contact = new Contact("");
        PhoneBookManager.create(name, TestUtils.randomInteger(1,5));

		PhoneBookManager.add(name, contact);

		exception.expect(InstanceNotFoundException.class);
		PhoneBookManager.add(notName, contact);

    }

    @Test
    public void find() throws InstanceAlreadyExistsException, SizeLimitExceededException, InvalidEmailException, InvalidPhoneNumberException, InstanceNotFoundException
	{
        String name = TestUtils.randomString(10);
        String notName = name+TestUtils.randomString(5);
        String cName = TestUtils.randomString(5);
        Contact contact = new Contact(cName);
        PhoneBookManager.create(name, TestUtils.randomInteger(1,5));
        PhoneBookManager.add(name, contact);
		PhoneBookManager.find(name, cName);
		exception.expect(InstanceNotFoundException.class);
        PhoneBookManager.find(notName, cName);
    }

    @Test
    public void toStringTest() throws InstanceAlreadyExistsException, SizeLimitExceededException, InvalidEmailException, InvalidPhoneNumberException, InstanceNotFoundException
	{
        String name = TestUtils.randomString(10);
        String notName = name+TestUtils.randomString(5);
		String cName = TestUtils.randomString(5);
		Contact contact = new Contact(cName);
        PhoneBookManager.create(name, TestUtils.randomInteger(1,5));
        PhoneBookManager.add(name, contact);
		assertNotEquals("", PhoneBookManager.toString(name));
		exception.expect(InstanceNotFoundException.class);
        PhoneBookManager.toString(notName);

    }

    @Test
    public void size() throws InstanceAlreadyExistsException
	{
        String name = TestUtils.randomString(10);

		try
		{
			PhoneBookManager.size(name);
			fail();
		} catch (InstanceNotFoundException ignored)
		{

		}
		PhoneBookManager.create(name, TestUtils.randomInteger(1,5));
		try
		{
			PhoneBookManager.size(name);
		} catch (InstanceNotFoundException e)
		{
			fail(e.getMessage());
		}
	}

}