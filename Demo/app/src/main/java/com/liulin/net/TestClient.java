package com.liulin.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.protobuf.ByteString;
import com.liulin.auto.packet.DrawProto;
import com.liulin.auto.packet.MessageId;
import com.liulin.auto.packet.ReqMsgProto;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;


/**
 * Created by liulin on 2016/6/22.
 */
public class TestClient {
    public static String HOST = "180.76.165.1";//180.76.165.1
    public static int PORT = 8000;
    public  Bootstrap bootstrap;
    public  Channel channel;
    private static TestClient testClient;
    private static NetCallBack callBack;
   public void init(){
       this.getBootstrap();
       this.getChannel(HOST, PORT);
   }
    public static TestClient instance( NetCallBack acallBack){
        callBack = acallBack;
        if(testClient==null){
            testClient = new TestClient();
        }
        return testClient;
    }
    public TestClient(){
        if(channel==null||bootstrap==null){
            init();
        }
    }
    public  final Bootstrap getBootstrap() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("decoder", new ByteArrayDecoder());
                pipeline.addLast("encoder", new ByteArrayEncoder());
                pipeline.addLast("handler", new TestClientHandler(callBack));
            }
        });
        b.option(ChannelOption.SO_KEEPALIVE, true);
        this.bootstrap = b;
        return b;
    }


    public  final Channel getChannel(String host, int port) {
        Channel channel = null;
        try {
            channel = bootstrap.connect(host, port).sync().channel();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(String.format("连接Server(IP[%s],PORT[%s])失败", host,port));
            return null;
        }
        this.channel = channel;
        return channel;
    }

    public  void sendImg(String picPath) {
        if(channel==null||bootstrap==null){
            init();
        }
        if(channel==null||bootstrap==null){
            callBack.onFailure("");
            return;
        }
        ReqMsgProto.ReqMsg.Builder req = ReqMsgProto.ReqMsg.newBuilder();
        req.setMessageId(MessageId.UPLOAD_PIC);
        DrawProto.UploadPIcReq.Builder pic = DrawProto.UploadPIcReq.newBuilder();
        File f = new File(picPath);
        if(!f.exists()){
            callBack.onFailure("上传图片不存在");
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(picPath);
        //File f = new File(picPath);
        //  BufferedImage bi;
        try {
//          bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//          ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            ByteString temp = ByteString.copyFrom(bytes);
            pic.setPic(temp);
            req.setUploadPIcReq(pic.build());
            channel.writeAndFlush(req.build().toByteArray());
        } catch (Exception e) {
            callBack.onFailure("上传图片失败");
            e.printStackTrace();
        }
    }

    public  void downLoadImg(String... parms) {
        if(channel==null||bootstrap==null){
            init();
        }
        if(channel==null||bootstrap==null){
            callBack.onFailure("");
            return;
        }
        int location = 1;
        if(parms!=null&&parms[0]!=null){
            try{
                location=Integer.parseInt(parms[0]);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        ReqMsgProto.ReqMsg.Builder req = ReqMsgProto.ReqMsg.newBuilder();
        req.setMessageId(MessageId.LOC_PIC);
        DrawProto.LockPIcReq.Builder builder = DrawProto.LockPIcReq.newBuilder();
        try {
            builder.setLocation(location);
            req.setLockPIcReq(builder.build());
            channel.writeAndFlush(req.build().toByteArray());
        } catch (Exception e) {
            callBack.onFailure("下载图片失败");
            e.printStackTrace();
        }
    }

 public void release(){
     channel.close();
     channel = null;
     bootstrap = null;
     this.testClient = null;
 }
}
