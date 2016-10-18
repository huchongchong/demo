package com.liulin.net;

import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.protobuf.ByteString;
import com.liulin.auto.packet.DrawProto;
import com.liulin.auto.packet.MessageId;
import com.liulin.auto.packet.ReqMsgProto;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by liulin on 2016/6/22.
 */
public class TestClientHandler extends SimpleChannelInboundHandler<Object> {
    private final NetCallBack netCallBack;

    public TestClientHandler(NetCallBack netCallBack) {
        this.netCallBack = netCallBack;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("success");
        Log.e("channelRead0", msg.toString());

        if(netCallBack!=null){netCallBack.onServerResponse(msg);}

       ReqMsgProto.ReqMsg reqMsg = ReqMsgProto.ReqMsg.parseFrom((byte[]) msg);
        if(reqMsg!=null){
            Log.e("qq","messageId"+reqMsg.getMessageId());
            switch (reqMsg.getMessageId()){
                case MessageId.LOC_PIC:
                    ByteString S = reqMsg.getLockPIcResp().getPic();
                    netCallBack.onDownLoadPic(BitmapFactory.decodeStream(S.newInput()));
                break;
                case MessageId.UPLOAD_PIC:
                    netCallBack.onServerResponse("上传图片成功");
                    break;
            }
        }
    }

}
