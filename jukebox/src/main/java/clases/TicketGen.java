package clases;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;



public class TicketGen {
	
	
	public Ticket genticket() throws UnsupportedEncodingException{
		String  ticket;
		   Random rn = new Random();
		   
	       int r = rn.nextInt();
	       System.out.println(r);
	       int k = rn.nextInt();
	      String a= Integer.toBinaryString(r);
	      String otro= Integer.toBinaryString(k);
	      System.out.println(a.length());
	      System.out.println(a);
	      System.out.println(otro.length());
	      System.out.println(otro);
	      
	      int x = r^k;
	      System.out.println("Primero: "+Integer.toBinaryString(r));
	      System.out.println("segundo: "+Integer.toBinaryString(k));
	      
	      System.out.println("Operacion XOR  "+Integer.toBinaryString(x));
	      System.out.println("operacion lenght: "+Integer.toBinaryString(x).length());
	      //En esta parte nos aseguramos que la salida es de 32 bits para poder mapear
	     
	      if (Integer.toBinaryString(x).length() < 32)
	      {
	    	    ticket=Integer.toBinaryString(x);
	    	  
	    	  while(ticket.length() < 32)
	    	  {
	    		 ticket="0"+ticket;
	    		  System.out.println("Esto es en el bucle: "+ticket);
	    		  System.out.println(ticket.length());
	    	  }
	    	  System.out.println("Este es el definitivo: "+ticket+ " y tiene "+ticket.length()+" bits de longitud");
	      }
	      else
	      {
	    	  ticket=Integer.toBinaryString(x);
	    	  System.out.println("Este es el definitivo: "+ticket+ " y tiene "+ticket.length()+" bits de longitud");
	      }
	      
	      //Separamos la salida en 5 partes de 6 bits + 2 de control y mapeamos
	      System.out.println("VAMOS A SEPARAR"+ticket.length());
	      String[] textElements = ticket.split("");
	      System.out.println(textElements.length);
	      String first = textElements[1]+textElements[2]+textElements[3]+textElements[4]+textElements[5]+textElements[6];
	      String second = textElements[7]+textElements[8]+textElements[9]+textElements[10]+textElements[11]+textElements[12];
	      String third = textElements[13]+textElements[14]+textElements[15]+textElements[16]+textElements[17]+textElements[18];
	      String fourth = textElements[19]+textElements[20]+textElements[21]+textElements[22]+textElements[23]+textElements[14];
	      String fifth = textElements[25]+textElements[26]+textElements[27]+textElements[28]+textElements[29]+textElements[30];
	      String control =textElements[31]+textElements[32];
	      System.out.println(first+second+third+fourth+fifth+"--"+ticket);
	      System.out.println(control);
	      int numero = first.length()+second.length()+third.length()+fourth.length()+fifth.length()+control.length();
	      System.out.println("Tamaño de la salida:"+numero);
	      System.out.println(first);
	      System.out.println(second);
	      System.out.println(third);
	      System.out.println(fourth);
	      System.out.println(fifth);
	      System.out.println(control);
	      
	      Mapping map = new Mapping();
	      String uno = map.mapear(first);
	      String dos = map.mapear(second);
	      String tres = map.mapear(third);
	      String cuatro = map.mapear(fourth);
	      String cinco = map.mapear(fifth);
	      System.out.println("Esta sera la clave enviada para el cliente:"+uno+dos+tres+cuatro+cinco);
	      String userkey= uno+dos+tres+cuatro+cinco;
	      byte ptext[] = userkey.getBytes();
	      String value = new String(ptext, "UTF-8");
	      System.out.println("-----------en utf-8"+value);
	     
	      String time =getTime();
	      Ticket ticketgenerated = new Ticket(userkey,time,"1");
	      DBOperations operations = new DBOperations();
	      operations.addTicket(ticketgenerated);
	       
		return ticketgenerated;
	}
	
	
	
	public static String getTime()
	{	
		
	
		
		java.util.Date date = new Date();
		System.out.println (date);
		 long lnMilisegundos = date.getTime();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
		System.out.println("sql.Timestamp: "+sqlTimestamp);
		
	
		String timestamp= sqlTimestamp.toString();
		System.out.println("--date--"+date);
		System.out.println("--timestamp--"+timestamp);

		
		
		return timestamp;
	}

}
