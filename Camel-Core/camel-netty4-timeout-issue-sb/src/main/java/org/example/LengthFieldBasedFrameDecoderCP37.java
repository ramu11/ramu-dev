package org.example;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class LengthFieldBasedFrameDecoderCP37 extends LengthFieldBasedFrameDecoder {

	public LengthFieldBasedFrameDecoderCP37(ByteOrder byteOrder, int maxFrameLength, int lengthFieldOffset,
			int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
		super(byteOrder, maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
	}

	public LengthFieldBasedFrameDecoderCP37(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
			int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
	}

	public LengthFieldBasedFrameDecoderCP37(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
	}

	public LengthFieldBasedFrameDecoderCP37(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
			int lengthAdjustment, int initialBytesToStrip) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
	}

	@Override
	protected long getUnadjustedFrameLength(ByteBuf buf, int offset, int length, ByteOrder order) {
		buf = buf.order(order);
		
		String encodedLength = buf.toString(offset, length, Charset.forName("Cp037"));
		long framelength = Long.valueOf(encodedLength);
		return framelength;
	}
}
