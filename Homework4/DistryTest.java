import static org.junit.Assert.*;
/**
 * Homework 4
 * @author Svilen Stefanov
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

public class DistryTest {
  private IResponse sendReceive(IRequest request) throws UnknownHostException, IOException, ClassNotFoundException {
    String masterHost = "127.0.0.1";
    int masterClientPort = 5555;
    
    Socket master = new Socket(masterHost, masterClientPort);
    try {
      ObjectOutputStream out = new ObjectOutputStream(master.getOutputStream());
      out.writeObject(request);
      out.flush();

      ObjectInputStream in = new ObjectInputStream(master.getInputStream());
      IResponse response = (IResponse) in.readObject();
      return response;

    } finally {
      master.close();
    }
  }
  
  private void insert(String key, int value) throws UnknownHostException, IOException, ClassNotFoundException {
    IRequest request = new StoreRequest(key, value);
    IResponse response = sendReceive(request);
    ResponseVisitor rv = new ResponseVisitor();
    Mutable<Boolean> isStoreResponse = new Mutable<>(false);
    rv.__( readResponse -> {
      fail();
    }, storeResponse -> {
      isStoreResponse.set(true);
    });
    response.accept(rv);
    assertTrue(isStoreResponse.get());
  }
  
  private SerializableOptional<Integer> lookup(String key) throws IOException, ClassNotFoundException {
    IRequest request = new ReadRequest(key);
    IResponse response = sendReceive(request);
    ResponseVisitor rv = new ResponseVisitor();
    Mutable<Boolean> isReadResponse = new Mutable<>();
    Mutable<ReadResponse> mutableReadResponse = new Mutable<>();
    rv.__( readResponse -> {
      isReadResponse.set(true);
      mutableReadResponse.set(readResponse);
    }, storeResponse -> {
      fail();
    });
    response.accept(rv);
    assertTrue(isReadResponse.get());
    return mutableReadResponse.get().getValue();
  }

  @Test
  public void test() throws UnknownHostException, ClassNotFoundException, IOException {
    insert("Hugo", 99);
    insert("Inge", 22);
    insert("Egon", 100);
    assertEquals(SerializableOptional.empty(), lookup("Atomfriedolina"));
    assertEquals(SerializableOptional.of(99), lookup("Hugo"));
    insert("Oliver", 111);
    insert("Rosa", 12);
    assertEquals(SerializableOptional.of(12), lookup("Rosa"));
    assertEquals(SerializableOptional.of(22), lookup("Inge"));
    assertEquals(SerializableOptional.of(100), lookup("Egon"));
    insert("Egon", 333);
    assertEquals(SerializableOptional.of(333), lookup("Egon"));
    assertEquals(SerializableOptional.of(22), lookup("Inge"));
    assertEquals(SerializableOptional.empty(), lookup("Rosamunde"));
    insert("Bert", 88);
    assertEquals(SerializableOptional.empty(), lookup("Peter"));
    insert("Peter", 66);
    assertEquals(SerializableOptional.of(12), lookup("Rosa"));
    assertEquals(SerializableOptional.of(88), lookup("Bert"));
    assertEquals(SerializableOptional.of(333), lookup("Egon"));
    assertEquals(SerializableOptional.of(66), lookup("Peter"));
  }

}
