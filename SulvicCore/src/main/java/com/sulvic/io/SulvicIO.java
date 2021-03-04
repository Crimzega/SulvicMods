package com.sulvic.io;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public class SulvicIO{
	
	public static void closeQuietly(Closeable... closeables){
		try{
			for(Closeable closeable: closeables){
				if(closeable instanceof Flushable) ((Flushable)closeable).flush();
				closeable.close();
			}
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
}
