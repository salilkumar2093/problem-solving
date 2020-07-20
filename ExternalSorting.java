package com.manager.parking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class ExternalizedSorting {
	// we divide the file into small blocks. If the blocks
	// are too small,? we shall create too many temporary files else If they are too
	// big, we shall be using too much memory.
	public static long estimateBestSizeOfBlocks(File filetobesorted) {
		long sizeoffile = filetobesorted.length();
		// we don't want to open up much more than 1024 temporary files, better run
		// out of memory first. (Even 1024 is stretching it.)
		final int MAXTEMPFILES = 1024;
		long blocksize = sizeoffile / MAXTEMPFILES;

		// File Size is 1000MB and RAM is 10MB so we get block size as 1000/1024=1
		// 1<100/2 it's true then block size will be 10/2 = 5;

		long freemem = Runtime.getRuntime().freeMemory();
		System.out.println("Free Memory " + freemem);
		if (blocksize < freemem / 2)
			blocksize = freemem / 2;
		else {
			if (blocksize >= freemem)
				System.err.println("We expect to run out of memory. ");
		}
		return blocksize;
	}

	public static void main(String... args) throws IOException {
		String inputfile = args[0];
		String outputfile = args[1];
		List<File> l = sortInBatch(new File(inputfile));
		mergeSortedFiles(l, new File(outputfile));
	}

	public static List<File> sortInBatch(File file) throws IOException {
		List<File> files = new ArrayList<File>();
		BufferedReader fbr = new BufferedReader(new FileReader(file));
		long blocksize = estimateBestSizeOfBlocks(file);// in bytes
		try {
			List<String> tmplist = new ArrayList<>();
			String line = "";
			try {
				while (line != null) {
					long currentblocksize = 0;// in bytes
					while ((currentblocksize < blocksize) && ((line = fbr.readLine()) != null)) {// as long you have 5MB
						tmplist.add(line);
						currentblocksize += line.length();
					}
					files.add(sortAndSave(tmplist));
					tmplist.clear();
				}
			} catch (EOFException oef) {
				if (tmplist.size() > 0) {
					files.add(sortAndSave(tmplist));
					tmplist.clear();
				}
			}
		} finally {
			fbr.close();
		}
		return files;
	}

	public static File sortAndSave(List<String> tmplist) throws IOException {
		Collections.sort(tmplist, new StringSortComparator()); //
		File newtmpfile = File.createTempFile("sortInBatch", "flatfile");
		newtmpfile.deleteOnExit();
		BufferedWriter fbw = new BufferedWriter(new FileWriter(newtmpfile));
		try {
			for (String r : tmplist) {
				fbw.write(r);
				fbw.newLine();
			}
		} finally {
			fbw.close();
		}
		return newtmpfile;
	}

	public static int mergeSortedFiles(List<File> files, File outputfile) throws IOException {
		PriorityQueue<FileObject> pq = new PriorityQueue<>(new FileSortComparator());
		for (File f : files) {
			FileObject bfb = new FileObject(f);
			pq.add(bfb);
		}
		BufferedWriter fbw = new BufferedWriter(new FileWriter(outputfile));
		int rowcounter = 0;
		try {
			while (pq.size() > 0) {
				FileObject bfb = pq.poll();
				int r = bfb.pop();
				fbw.write(r);
				++rowcounter;
				if (bfb.empty()) {
					bfb.fbr.close();
					bfb.originalfile.delete();// we don't need you anymore
				} else {
					pq.add(bfb); // add it back
				}
			}
		} finally {
			fbw.close();
			for (FileObject bfb : pq)
				bfb.close();
		}
		return rowcounter;
	}
}

class FileObject {
	public static int BUFFERSIZE = 2048;
	public static int LIST_SIZE = 10;
	// File
	public BufferedReader fbr;
	public File originalfile;
	private int cache;
	private boolean empty = false;

	List<Integer> list;

	public FileObject(File f) throws IOException {
		originalfile = f;
		fbr = new BufferedReader(new FileReader(f), BUFFERSIZE);
		list = new LinkedList<>();
		reload();
	}

	public boolean empty() {
		return this.empty;
	}

	private void reload() throws IOException {
		try {
			while (this.list.size() != LIST_SIZE && this.cache != -1) {
				this.cache = fbr.read();
				if (this.cache == -1) {
					this.empty = true;
					this.cache = Integer.MIN_VALUE;
					return;
				}
				this.list.add(cache);
			}

		} catch (EOFException oef) {
			this.empty = true;
			this.cache = Integer.MIN_VALUE;
		}
	}

	public Integer peek() {
		return list.isEmpty() ? null : this.list.get(0);
	}

	private Integer poll() {
		return list.isEmpty() ? null : this.list.remove(0);
	}

	public Integer pop() throws IOException {
		Integer answer = poll();
		reload();
		return answer;
	}

	public void close() throws IOException {
		fbr.close();
	}

}

class FileSortComparator implements Comparator<FileObject> {
	@Override
	public int compare(FileObject o1, FileObject o2) {
		return o1.peek().compareTo(o2.peek());
	}

}

class StringSortComparator implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		return o1.compareTo(o2);
	}
}
