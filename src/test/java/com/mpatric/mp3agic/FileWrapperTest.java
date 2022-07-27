package com.mpatric.mp3agic;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileWrapperTest {
	private static final String fs = File.separator;

	private static final String VALID_FILENAME = "notags.mp3";

	private static final String VALID_FILEPATH = "src" + fs + "test" + fs + "resources" + fs + VALID_FILENAME;

	private static final long VALID_FILE_LENGTH = 2869;

	private static final String NON_EXISTENT_FILENAME = "just-not.there";

	private static final String MALFORMED_FILENAME = "malformed.\0";

	@Test
	public void shouldReadValidFilename() throws IOException {
		FileWrapper fileWrapper = new FileWrapper(VALID_FILEPATH);
		System.out.println(fileWrapper.getFilename());
		System.out.println(VALID_FILEPATH);
		assertEquals(VALID_FILENAME, fileWrapper.getFilename());
	}

	@Test
	public void shouldReadValidFilepath() throws IOException {
		FileWrapper fileWrapper = new FileWrapper(VALID_FILEPATH);
		System.out.println(fileWrapper.getFilename());
		System.out.println(VALID_FILEPATH);
		assertEquals(VALID_FILEPATH, fileWrapper.getFilePath());
		assertTrue(fileWrapper.getLastModified() > 0);
		assertEquals(VALID_FILE_LENGTH, fileWrapper.getLength());
	}

	@Test
	public void shouldReadValidFileWithPath() throws IOException {
		FileWrapper fileWrapper = new FileWrapper(new File(VALID_FILEPATH));
		System.out.println(fileWrapper.getFilename());
		System.out.println(VALID_FILEPATH);
		assertEquals(VALID_FILEPATH, fileWrapper.getFilePath());
		assertTrue(fileWrapper.getLastModified() > 0);
		assertEquals(VALID_FILE_LENGTH, fileWrapper.getLength());
	}

	@Test
	public void shouldReadValidPath() throws IOException {
		FileWrapper fileWrapper = new FileWrapper(Paths.get(VALID_FILEPATH));
		System.out.println(fileWrapper.getFilename());
		System.out.println(VALID_FILEPATH);
		assertEquals(VALID_FILEPATH, fileWrapper.getFilePath());
		assertTrue(fileWrapper.getLastModified() > 0);
		assertEquals(VALID_FILE_LENGTH, fileWrapper.getLength());
	}

	@Test(expected = FileNotFoundException.class)
	public void shouldFailForNonExistentFile() throws IOException {
		new FileWrapper(NON_EXISTENT_FILENAME);
	}

	@Test(expected = InvalidPathException.class)
	public void shouldFailForMalformedFilename() throws IOException {
		new FileWrapper(MALFORMED_FILENAME);
	}

	@Test(expected = NullPointerException.class)
	public void shouldFailForNullFilename() throws IOException {
		new FileWrapper((String) null);
	}

	@Test(expected = NullPointerException.class)
	public void shouldFailForNullFilenameFile() throws IOException {
		new FileWrapper((java.io.File) null);
	}

	@Test(expected = NullPointerException.class)
	public void shouldFailForNullPath() throws IOException {
		new FileWrapper((java.nio.file.Path) null);
	}
}
