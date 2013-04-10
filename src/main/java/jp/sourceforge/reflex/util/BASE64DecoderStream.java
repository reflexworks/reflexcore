package jp.sourceforge.reflex.util;

import java.io.InputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.EOFException;

/**
 * Decodes the input data using the BASE64 transformation as specified in <A
 * HREF="http://www.faqs.org/rfcs/rfc2045.html">RFC 2045</A>, section 6.8, and
 * outputs the decoded data to the underlying input stream.
 * 
 * @author David A. Herman
 * @version 1.0 of September 2000
 * @see java.io.FilterInputStream
 **/
public class BASE64DecoderStream extends FilterInputStream {

	/**
	 * The internal lookup table of BASE64 character values to their byte
	 * values.
	 **/
	private static final byte codes[] = new byte[256];

	/**
	 * Fills the lookup table of BASE64 character values with their byte values.
	 **/
	static {
		for (int i = 0; i < 256; i++)
			codes[i] = -1;
		for (char c = 'A'; c <= 'Z'; c++)
			codes[c] = (byte) (c - 'A');
		for (char c = 'a'; c <= 'z'; c++)
			codes[c] = (byte) (26 + (c - 'a'));
		for (char c = '0'; c <= '9'; c++)
			codes[c] = (byte) (52 + (c - '0'));
		codes['+'] = (byte) 62;
		codes['/'] = (byte) 63;
		codes['='] = (byte) 64;
	}

	/**
	 * The internal buffer of encoded input bytes.
	 **/
	private byte input[] = new byte[4];

	/**
	 * The internal buffer of output bytes to be decoded.
	 **/
	private byte output[] = new byte[3];

	/**
	 * The index of the next position in the internal buffer of output bytes at
	 * which to store output.
	 **/
	private int outputIndex = 0;

	/**
	 * The number of decoded bytes left to return from the internal buffer.
	 **/
	private int remaining = 3;

	/**
	 * Builds a BASE64 decoding stream on top of the given underlying input
	 * stream.
	 **/
	public BASE64DecoderStream(InputStream in) {
		super(in);
	}

	/**
	 * Returns one byte of data decoded from the underlying input stream. This
	 * means that either 0 or 4 bytes will have to be read from the underlying
	 * stream; 0 if there is more data in the internal buffer to read from, and
	 * 4 if the internal buffer has been exhausted and needs to be refilled.
	 * 
	 * @throws IOException
	 *             if an I/O error occurs.
	 **/
	public int read() throws IOException {
		// If the number of remaining bytes is 0, then
		// don't try to load anything, and automatically
		// return -1, indicating that the end of the
		// stream has been reached.
		if (remaining == 0)
			return -1;

		// If the ouputIndex is 0, load in the next 4 bytes
		// and decode them from BASE64 into 3 bytes of data.
		// Store those 3 bytes in the output array.
		if (outputIndex == 0) {
			// Load in the next 4 bytes. If a byte is not a
			// recognized element of the BASE64 alphabet,
			// skip it and try the next byte.
			int i = 0;
			while (i < 4) {
				int r = in.read();
				// If the read method of the underlying input
				// stream returns -1, there is no more input,
				// so set the number of remaining bytes to 0
				// to prevent any further reading.
				if (r == -1) {
					remaining = 0;

					// If this was the beginning of a BASE64 quantum
					// (four characters of input), then return -1.
					if (i == 0)
						return -1;

					// If the decoder was in the middle of a BASE64
					// quantum, there was an unexpected I/O error.
					throw new EOFException("Unexpected end of input.");
				}

				// Look up the byte in the BASE64 code table.
				input[i] = codes[r];

				// If the byte is a valid member of the BASE64
				// alphabet, increment the counter to load the
				// next of the four input bytes.
				if (input[i] != -1)
					i++;
			}
			output[0] = (byte) ((input[0] << 2) + ((input[1] & 0x30) >> 4));
			if (input[2] == 64)
				remaining = 1;
			else {
				output[1] = (byte) (((input[1] & 0x0F) << 4) + ((input[2] & 0x3C) >> 2));
				if (input[3] == 64)
					remaining = 2;
				else
					output[2] = (byte) (((input[2] & 0x03) << 6) + input[3]);
			}
		}

		// Return the next element in the output array.
		// If the output array has just been filled in with
		// 3 more bytes, the first element will be returned.
		int ret = (output[outputIndex] & 0xFF);

		// Increment the outputIndex, modulus 3.
		outputIndex = (outputIndex + 1) % 3;

		// If an equal sign ('=') has been read in somewhere,
		// then the number of remaining bytes is decreasing.
		if (remaining < 3)
			remaining--;

		// Return the element from the output array.
		return ret;
	}

	/**
	 * Fills the given byte array with data decoded from the underlying input
	 * stream. If there is no more available data because the input stream has
	 * been exhausted, the value <code>-1</code> is returned and no data is
	 * written to the array.
	 * 
	 * @param b
	 *            the byte array to fill with decoded data from the underlying
	 *            input stream.
	 * @return the number of bytes written to the array, or <code>-1</code> if
	 *         the underlying input stream has been exhausted.
	 * @throws IOException
	 *             if an I/O error occurs.
	 **/
	public int read(byte b[]) throws IOException {
		return read(b, 0, b.length);
	}

	/**
	 * Fills <code>len</code> bytes in the given byte array starting at offset
	 * <code>off</code> with data decoded from the underlying input stream. Note
	 * that this means that more than <code>len</code> bytes of data will be
	 * read from the underlying stream, since data encoded with the BASE64
	 * transformation is 33 percent larger than the unencoded raw data. If there
	 * is no more available data because the input stream has been exhausted,
	 * the value <code>-1</code> is returned and no data is written to the
	 * array.
	 * 
	 * @param b
	 *            the byte array to fill with decoded data from the underlying
	 *            input stream.
	 * @param off
	 *            the offset at which to start filling in the array.
	 * @param len
	 *            the number of bytes to write to the array.
	 * @return the number of decoded bytes written to the array, or
	 *         <code>-1</code> if the stream has been exhausted. Note that this
	 *         is not the same as the number of bytes read from the underlying
	 *         input stream.
	 * @throws IOException
	 *             if an I/O error occurs.
	 **/
	public int read(byte b[], int off, int len) throws IOException {
		for (int i = 0; i < len; i++) {
			int n = read();
			// If the underlying input stream is exhausted
			// before len bytes are read, return the number
			// of bytes that have been read so far.
			if (n == -1)
				return (i == 0 ? -1 : i);
			b[off + i] = (byte) n;
		}
		// If the loop terminates successfully, then all len
		// bytes have been loaded, so return len.
		return len;
	}

	/**
	 * Returns the number of bytes that can be read from the stream without
	 * blocking, or <code>-1</code> if the stream has been exhausted and
	 * decoding is finished. The number of available bytes is otherwise equal to
	 * the number of bytes that can be read from the underlying stream (
	 * <code>in.available</code>) plus the number of bytes stored in the
	 * internal buffer that have been read from the underlying stream but not
	 * yet decoded and read from this stream.
	 * 
	 * @return the number of bytes that can be read from the stream without
	 *         blocking, or <code>-1</code> if the stream has been exhausted and
	 *         decoding is finished.
	 * @throws IOException
	 *             if an I/O error occurs.
	 **/
	public int available() throws IOException {
		if (remaining == 0)
			return -1;
		return in.available() + outputIndex;
	}

	/**
	 * <I>For testing.</I> Takes the first command line argument as a binary
	 * output file name and the second command line argument as a text input
	 * file name, reads in the input file as BASE64-encoded data, and outputs
	 * the decoded binary data to the output file.
	 * 
	 * @param args
	 *            the command-line arguments.
	 **/
	public static void main(String args[]) {
		try {
			java.io.FileOutputStream out = new java.io.FileOutputStream(args[1]);
			BASE64DecoderStream in = new BASE64DecoderStream(
					new java.io.FileInputStream(args[0]));
			int b = in.read();
			while (b != -1) {
				out.write(b);
				b = in.read();
			}
			in.close();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
