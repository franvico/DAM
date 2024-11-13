package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputStreamNoHeader extends ObjectOutputStream{

	public ObjectOutputStreamNoHeader(FileOutputStream f) throws IOException {
		super(f);
		
	}
	
	@Override
	public void writeStreamHeader() {
		
	}
	
}
