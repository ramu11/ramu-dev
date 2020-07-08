package org.example;

import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.component.netty4.ChannelHandlerFactory;
import org.apache.camel.component.netty4.DefaultChannelHandlerFactory;

public class ByteArrayCodecFactory {
private static final int FIELD_LENGTH = 20;
private static final int LENGTH_FIELD_OFFSET = 0;
private static final int MAX_BUFFER_LENGTH = 1048576;

	/*
	public static ChannelHandlerFactory newByteArrayHandlerFactory(){
		return new DefaultChannelHandlerFactory() {
			
			@Override
			public ChannelHandler newChannelHandler() {
				System.out.println("Instantiating new ByteArrayDecoder Instance at "+new Date());
				return new ByteArrayDecoder();
			}
		};
	}
*/
	public static List<ChannelHandlerFactory> getDecoders() {
		List<ChannelHandlerFactory> decoders = new ArrayList<>();
		
		
		decoders.add(newLengthFieldBasedFrameDecoder());
		decoders.add(newByteArrayDecoder());
		
		return decoders;
	}

	private static DefaultChannelHandlerFactory newLengthFieldBasedFrameDecoder() {
		return new DefaultChannelHandlerFactory() {
			@Override
			public ChannelHandler newChannelHandler() {
				return new LengthFieldBasedFrameDecoderCP37(MAX_BUFFER_LENGTH,
						LENGTH_FIELD_OFFSET, FIELD_LENGTH);
			}
		};
	}
	
    public static ChannelHandlerFactory newByteArrayDecoder() {
        return new DefaultChannelHandlerFactory() {
			@Override
			public ChannelHandler newChannelHandler() {
				return new ByteArrayDecoder();
			}
		};
    }

    public static ChannelHandlerFactory newByteArrayEncoder() {
        return new DefaultChannelHandlerFactory() {
			@Override
			public ChannelHandler newChannelHandler() {
				return new ByteArrayEncoder();
			}
		};
    }
}
