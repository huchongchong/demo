// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: req.proto

package com.liulin.auto.packet;

public final class ReqMsgProto {
  private ReqMsgProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ReqMsgOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 messageId = 1;
    boolean hasMessageId();
    int getMessageId();
    
    // optional int64 userId = 2;
    boolean hasUserId();
    long getUserId();
    
    // optional .UploadPIcReq uploadPIcReq = 10001;
    boolean hasUploadPIcReq();
    com.liulin.auto.packet.DrawProto.UploadPIcReq getUploadPIcReq();
    com.liulin.auto.packet.DrawProto.UploadPIcReqOrBuilder getUploadPIcReqOrBuilder();
    
    // optional .LockPIcReq lockPIcReq = 10003;
    boolean hasLockPIcReq();
    com.liulin.auto.packet.DrawProto.LockPIcReq getLockPIcReq();
    com.liulin.auto.packet.DrawProto.LockPIcReqOrBuilder getLockPIcReqOrBuilder();
    
    // optional .LockPIcResp lockPIcResp = 10004;
    boolean hasLockPIcResp();
    com.liulin.auto.packet.DrawProto.LockPIcResp getLockPIcResp();
    com.liulin.auto.packet.DrawProto.LockPIcRespOrBuilder getLockPIcRespOrBuilder();
  }
  public static final class ReqMsg extends
      com.google.protobuf.GeneratedMessage
      implements ReqMsgOrBuilder {
    // Use ReqMsg.newBuilder() to construct.
    private ReqMsg(Builder builder) {
      super(builder);
    }
    private ReqMsg(boolean noInit) {}
    
    private static final ReqMsg defaultInstance;
    public static ReqMsg getDefaultInstance() {
      return defaultInstance;
    }
    
    public ReqMsg getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ReqMsgProto.internal_static_ReqMsg_descriptor;
    }
    
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ReqMsgProto.internal_static_ReqMsg_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 messageId = 1;
    public static final int MESSAGEID_FIELD_NUMBER = 1;
    private int messageId_;
    public boolean hasMessageId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public int getMessageId() {
      return messageId_;
    }
    
    // optional int64 userId = 2;
    public static final int USERID_FIELD_NUMBER = 2;
    private long userId_;
    public boolean hasUserId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public long getUserId() {
      return userId_;
    }
    
    // optional .UploadPIcReq uploadPIcReq = 10001;
    public static final int UPLOADPICREQ_FIELD_NUMBER = 10001;
    private com.liulin.auto.packet.DrawProto.UploadPIcReq uploadPIcReq_;
    public boolean hasUploadPIcReq() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public com.liulin.auto.packet.DrawProto.UploadPIcReq getUploadPIcReq() {
      return uploadPIcReq_;
    }
    public com.liulin.auto.packet.DrawProto.UploadPIcReqOrBuilder getUploadPIcReqOrBuilder() {
      return uploadPIcReq_;
    }
    
    // optional .LockPIcReq lockPIcReq = 10003;
    public static final int LOCKPICREQ_FIELD_NUMBER = 10003;
    private com.liulin.auto.packet.DrawProto.LockPIcReq lockPIcReq_;
    public boolean hasLockPIcReq() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public com.liulin.auto.packet.DrawProto.LockPIcReq getLockPIcReq() {
      return lockPIcReq_;
    }
    public com.liulin.auto.packet.DrawProto.LockPIcReqOrBuilder getLockPIcReqOrBuilder() {
      return lockPIcReq_;
    }
    
    // optional .LockPIcResp lockPIcResp = 10004;
    public static final int LOCKPICRESP_FIELD_NUMBER = 10004;
    private com.liulin.auto.packet.DrawProto.LockPIcResp lockPIcResp_;
    public boolean hasLockPIcResp() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    public com.liulin.auto.packet.DrawProto.LockPIcResp getLockPIcResp() {
      return lockPIcResp_;
    }
    public com.liulin.auto.packet.DrawProto.LockPIcRespOrBuilder getLockPIcRespOrBuilder() {
      return lockPIcResp_;
    }
    
    private void initFields() {
      messageId_ = 0;
      userId_ = 0L;
      uploadPIcReq_ = com.liulin.auto.packet.DrawProto.UploadPIcReq.getDefaultInstance();
      lockPIcReq_ = com.liulin.auto.packet.DrawProto.LockPIcReq.getDefaultInstance();
      lockPIcResp_ = com.liulin.auto.packet.DrawProto.LockPIcResp.getDefaultInstance();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;
      
      memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, messageId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt64(2, userId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeMessage(10001, uploadPIcReq_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeMessage(10003, lockPIcReq_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeMessage(10004, lockPIcResp_);
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, messageId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(2, userId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(10001, uploadPIcReq_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(10003, lockPIcReq_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(10004, lockPIcResp_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    private static final long serialVersionUID = 0L;
    @Override
    protected Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }
    
    public static ReqMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static ReqMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static ReqMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static ReqMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static ReqMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static ReqMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static ReqMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static ReqMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static ReqMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static ReqMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(ReqMsg prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements ReqMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return ReqMsgProto.internal_static_ReqMsg_descriptor;
      }
      
      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return ReqMsgProto.internal_static_ReqMsg_fieldAccessorTable;
      }
      
      // Construct using com.liulin.auto.packet.ReqMsgProto.ReqMsg.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
          getUploadPIcReqFieldBuilder();
          getLockPIcReqFieldBuilder();
          getLockPIcRespFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }
      
      public Builder clear() {
        super.clear();
        messageId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        userId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000002);
        if (uploadPIcReqBuilder_ == null) {
          uploadPIcReq_ = com.liulin.auto.packet.DrawProto.UploadPIcReq.getDefaultInstance();
        } else {
          uploadPIcReqBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000004);
        if (lockPIcReqBuilder_ == null) {
          lockPIcReq_ = com.liulin.auto.packet.DrawProto.LockPIcReq.getDefaultInstance();
        } else {
          lockPIcReqBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000008);
        if (lockPIcRespBuilder_ == null) {
          lockPIcResp_ = com.liulin.auto.packet.DrawProto.LockPIcResp.getDefaultInstance();
        } else {
          lockPIcRespBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return ReqMsg.getDescriptor();
      }
      
      public ReqMsg getDefaultInstanceForType() {
        return ReqMsg.getDefaultInstance();
      }
      
      public ReqMsg build() {
        ReqMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private ReqMsg buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        ReqMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public ReqMsg buildPartial() {
        ReqMsg result = new ReqMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.messageId_ = messageId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.userId_ = userId_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        if (uploadPIcReqBuilder_ == null) {
          result.uploadPIcReq_ = uploadPIcReq_;
        } else {
          result.uploadPIcReq_ = uploadPIcReqBuilder_.build();
        }
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        if (lockPIcReqBuilder_ == null) {
          result.lockPIcReq_ = lockPIcReq_;
        } else {
          result.lockPIcReq_ = lockPIcReqBuilder_.build();
        }
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        if (lockPIcRespBuilder_ == null) {
          result.lockPIcResp_ = lockPIcResp_;
        } else {
          result.lockPIcResp_ = lockPIcRespBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof ReqMsg) {
          return mergeFrom((ReqMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(ReqMsg other) {
        if (other == ReqMsg.getDefaultInstance()) return this;
        if (other.hasMessageId()) {
          setMessageId(other.getMessageId());
        }
        if (other.hasUserId()) {
          setUserId(other.getUserId());
        }
        if (other.hasUploadPIcReq()) {
          mergeUploadPIcReq(other.getUploadPIcReq());
        }
        if (other.hasLockPIcReq()) {
          mergeLockPIcReq(other.getLockPIcReq());
        }
        if (other.hasLockPIcResp()) {
          mergeLockPIcResp(other.getLockPIcResp());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              messageId_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              userId_ = input.readInt64();
              break;
            }
            case 80010: {
              com.liulin.auto.packet.DrawProto.UploadPIcReq.Builder subBuilder = com.liulin.auto.packet.DrawProto.UploadPIcReq.newBuilder();
              if (hasUploadPIcReq()) {
                subBuilder.mergeFrom(getUploadPIcReq());
              }
              input.readMessage(subBuilder, extensionRegistry);
              setUploadPIcReq(subBuilder.buildPartial());
              break;
            }
            case 80026: {
              com.liulin.auto.packet.DrawProto.LockPIcReq.Builder subBuilder = com.liulin.auto.packet.DrawProto.LockPIcReq.newBuilder();
              if (hasLockPIcReq()) {
                subBuilder.mergeFrom(getLockPIcReq());
              }
              input.readMessage(subBuilder, extensionRegistry);
              setLockPIcReq(subBuilder.buildPartial());
              break;
            }
            case 80034: {
              com.liulin.auto.packet.DrawProto.LockPIcResp.Builder subBuilder = com.liulin.auto.packet.DrawProto.LockPIcResp.newBuilder();
              if (hasLockPIcResp()) {
                subBuilder.mergeFrom(getLockPIcResp());
              }
              input.readMessage(subBuilder, extensionRegistry);
              setLockPIcResp(subBuilder.buildPartial());
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 messageId = 1;
      private int messageId_ ;
      public boolean hasMessageId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      public int getMessageId() {
        return messageId_;
      }
      public Builder setMessageId(int value) {
        bitField0_ |= 0x00000001;
        messageId_ = value;
        onChanged();
        return this;
      }
      public Builder clearMessageId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        messageId_ = 0;
        onChanged();
        return this;
      }
      
      // optional int64 userId = 2;
      private long userId_ ;
      public boolean hasUserId() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public long getUserId() {
        return userId_;
      }
      public Builder setUserId(long value) {
        bitField0_ |= 0x00000002;
        userId_ = value;
        onChanged();
        return this;
      }
      public Builder clearUserId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        userId_ = 0L;
        onChanged();
        return this;
      }
      
      // optional .UploadPIcReq uploadPIcReq = 10001;
      private com.liulin.auto.packet.DrawProto.UploadPIcReq uploadPIcReq_ = com.liulin.auto.packet.DrawProto.UploadPIcReq.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.liulin.auto.packet.DrawProto.UploadPIcReq, com.liulin.auto.packet.DrawProto.UploadPIcReq.Builder, com.liulin.auto.packet.DrawProto.UploadPIcReqOrBuilder> uploadPIcReqBuilder_;
      public boolean hasUploadPIcReq() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public com.liulin.auto.packet.DrawProto.UploadPIcReq getUploadPIcReq() {
        if (uploadPIcReqBuilder_ == null) {
          return uploadPIcReq_;
        } else {
          return uploadPIcReqBuilder_.getMessage();
        }
      }
      public Builder setUploadPIcReq(com.liulin.auto.packet.DrawProto.UploadPIcReq value) {
        if (uploadPIcReqBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          uploadPIcReq_ = value;
          onChanged();
        } else {
          uploadPIcReqBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000004;
        return this;
      }
      public Builder setUploadPIcReq(
          com.liulin.auto.packet.DrawProto.UploadPIcReq.Builder builderForValue) {
        if (uploadPIcReqBuilder_ == null) {
          uploadPIcReq_ = builderForValue.build();
          onChanged();
        } else {
          uploadPIcReqBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000004;
        return this;
      }
      public Builder mergeUploadPIcReq(com.liulin.auto.packet.DrawProto.UploadPIcReq value) {
        if (uploadPIcReqBuilder_ == null) {
          if (((bitField0_ & 0x00000004) == 0x00000004) &&
              uploadPIcReq_ != com.liulin.auto.packet.DrawProto.UploadPIcReq.getDefaultInstance()) {
            uploadPIcReq_ =
              com.liulin.auto.packet.DrawProto.UploadPIcReq.newBuilder(uploadPIcReq_).mergeFrom(value).buildPartial();
          } else {
            uploadPIcReq_ = value;
          }
          onChanged();
        } else {
          uploadPIcReqBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000004;
        return this;
      }
      public Builder clearUploadPIcReq() {
        if (uploadPIcReqBuilder_ == null) {
          uploadPIcReq_ = com.liulin.auto.packet.DrawProto.UploadPIcReq.getDefaultInstance();
          onChanged();
        } else {
          uploadPIcReqBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }
      public com.liulin.auto.packet.DrawProto.UploadPIcReq.Builder getUploadPIcReqBuilder() {
        bitField0_ |= 0x00000004;
        onChanged();
        return getUploadPIcReqFieldBuilder().getBuilder();
      }
      public com.liulin.auto.packet.DrawProto.UploadPIcReqOrBuilder getUploadPIcReqOrBuilder() {
        if (uploadPIcReqBuilder_ != null) {
          return uploadPIcReqBuilder_.getMessageOrBuilder();
        } else {
          return uploadPIcReq_;
        }
      }
      private com.google.protobuf.SingleFieldBuilder<
          com.liulin.auto.packet.DrawProto.UploadPIcReq, com.liulin.auto.packet.DrawProto.UploadPIcReq.Builder, com.liulin.auto.packet.DrawProto.UploadPIcReqOrBuilder> 
          getUploadPIcReqFieldBuilder() {
        if (uploadPIcReqBuilder_ == null) {
          uploadPIcReqBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.liulin.auto.packet.DrawProto.UploadPIcReq, com.liulin.auto.packet.DrawProto.UploadPIcReq.Builder, com.liulin.auto.packet.DrawProto.UploadPIcReqOrBuilder>(
                  uploadPIcReq_,
                  getParentForChildren(),
                  isClean());
          uploadPIcReq_ = null;
        }
        return uploadPIcReqBuilder_;
      }
      
      // optional .LockPIcReq lockPIcReq = 10003;
      private com.liulin.auto.packet.DrawProto.LockPIcReq lockPIcReq_ = com.liulin.auto.packet.DrawProto.LockPIcReq.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.liulin.auto.packet.DrawProto.LockPIcReq, com.liulin.auto.packet.DrawProto.LockPIcReq.Builder, com.liulin.auto.packet.DrawProto.LockPIcReqOrBuilder> lockPIcReqBuilder_;
      public boolean hasLockPIcReq() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      public com.liulin.auto.packet.DrawProto.LockPIcReq getLockPIcReq() {
        if (lockPIcReqBuilder_ == null) {
          return lockPIcReq_;
        } else {
          return lockPIcReqBuilder_.getMessage();
        }
      }
      public Builder setLockPIcReq(com.liulin.auto.packet.DrawProto.LockPIcReq value) {
        if (lockPIcReqBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          lockPIcReq_ = value;
          onChanged();
        } else {
          lockPIcReqBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000008;
        return this;
      }
      public Builder setLockPIcReq(
          com.liulin.auto.packet.DrawProto.LockPIcReq.Builder builderForValue) {
        if (lockPIcReqBuilder_ == null) {
          lockPIcReq_ = builderForValue.build();
          onChanged();
        } else {
          lockPIcReqBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000008;
        return this;
      }
      public Builder mergeLockPIcReq(com.liulin.auto.packet.DrawProto.LockPIcReq value) {
        if (lockPIcReqBuilder_ == null) {
          if (((bitField0_ & 0x00000008) == 0x00000008) &&
              lockPIcReq_ != com.liulin.auto.packet.DrawProto.LockPIcReq.getDefaultInstance()) {
            lockPIcReq_ =
              com.liulin.auto.packet.DrawProto.LockPIcReq.newBuilder(lockPIcReq_).mergeFrom(value).buildPartial();
          } else {
            lockPIcReq_ = value;
          }
          onChanged();
        } else {
          lockPIcReqBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000008;
        return this;
      }
      public Builder clearLockPIcReq() {
        if (lockPIcReqBuilder_ == null) {
          lockPIcReq_ = com.liulin.auto.packet.DrawProto.LockPIcReq.getDefaultInstance();
          onChanged();
        } else {
          lockPIcReqBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }
      public com.liulin.auto.packet.DrawProto.LockPIcReq.Builder getLockPIcReqBuilder() {
        bitField0_ |= 0x00000008;
        onChanged();
        return getLockPIcReqFieldBuilder().getBuilder();
      }
      public com.liulin.auto.packet.DrawProto.LockPIcReqOrBuilder getLockPIcReqOrBuilder() {
        if (lockPIcReqBuilder_ != null) {
          return lockPIcReqBuilder_.getMessageOrBuilder();
        } else {
          return lockPIcReq_;
        }
      }
      private com.google.protobuf.SingleFieldBuilder<
          com.liulin.auto.packet.DrawProto.LockPIcReq, com.liulin.auto.packet.DrawProto.LockPIcReq.Builder, com.liulin.auto.packet.DrawProto.LockPIcReqOrBuilder> 
          getLockPIcReqFieldBuilder() {
        if (lockPIcReqBuilder_ == null) {
          lockPIcReqBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.liulin.auto.packet.DrawProto.LockPIcReq, com.liulin.auto.packet.DrawProto.LockPIcReq.Builder, com.liulin.auto.packet.DrawProto.LockPIcReqOrBuilder>(
                  lockPIcReq_,
                  getParentForChildren(),
                  isClean());
          lockPIcReq_ = null;
        }
        return lockPIcReqBuilder_;
      }
      
      // optional .LockPIcResp lockPIcResp = 10004;
      private com.liulin.auto.packet.DrawProto.LockPIcResp lockPIcResp_ = com.liulin.auto.packet.DrawProto.LockPIcResp.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.liulin.auto.packet.DrawProto.LockPIcResp, com.liulin.auto.packet.DrawProto.LockPIcResp.Builder, com.liulin.auto.packet.DrawProto.LockPIcRespOrBuilder> lockPIcRespBuilder_;
      public boolean hasLockPIcResp() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      public com.liulin.auto.packet.DrawProto.LockPIcResp getLockPIcResp() {
        if (lockPIcRespBuilder_ == null) {
          return lockPIcResp_;
        } else {
          return lockPIcRespBuilder_.getMessage();
        }
      }
      public Builder setLockPIcResp(com.liulin.auto.packet.DrawProto.LockPIcResp value) {
        if (lockPIcRespBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          lockPIcResp_ = value;
          onChanged();
        } else {
          lockPIcRespBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000010;
        return this;
      }
      public Builder setLockPIcResp(
          com.liulin.auto.packet.DrawProto.LockPIcResp.Builder builderForValue) {
        if (lockPIcRespBuilder_ == null) {
          lockPIcResp_ = builderForValue.build();
          onChanged();
        } else {
          lockPIcRespBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000010;
        return this;
      }
      public Builder mergeLockPIcResp(com.liulin.auto.packet.DrawProto.LockPIcResp value) {
        if (lockPIcRespBuilder_ == null) {
          if (((bitField0_ & 0x00000010) == 0x00000010) &&
              lockPIcResp_ != com.liulin.auto.packet.DrawProto.LockPIcResp.getDefaultInstance()) {
            lockPIcResp_ =
              com.liulin.auto.packet.DrawProto.LockPIcResp.newBuilder(lockPIcResp_).mergeFrom(value).buildPartial();
          } else {
            lockPIcResp_ = value;
          }
          onChanged();
        } else {
          lockPIcRespBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000010;
        return this;
      }
      public Builder clearLockPIcResp() {
        if (lockPIcRespBuilder_ == null) {
          lockPIcResp_ = com.liulin.auto.packet.DrawProto.LockPIcResp.getDefaultInstance();
          onChanged();
        } else {
          lockPIcRespBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }
      public com.liulin.auto.packet.DrawProto.LockPIcResp.Builder getLockPIcRespBuilder() {
        bitField0_ |= 0x00000010;
        onChanged();
        return getLockPIcRespFieldBuilder().getBuilder();
      }
      public com.liulin.auto.packet.DrawProto.LockPIcRespOrBuilder getLockPIcRespOrBuilder() {
        if (lockPIcRespBuilder_ != null) {
          return lockPIcRespBuilder_.getMessageOrBuilder();
        } else {
          return lockPIcResp_;
        }
      }
      private com.google.protobuf.SingleFieldBuilder<
          com.liulin.auto.packet.DrawProto.LockPIcResp, com.liulin.auto.packet.DrawProto.LockPIcResp.Builder, com.liulin.auto.packet.DrawProto.LockPIcRespOrBuilder> 
          getLockPIcRespFieldBuilder() {
        if (lockPIcRespBuilder_ == null) {
          lockPIcRespBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.liulin.auto.packet.DrawProto.LockPIcResp, com.liulin.auto.packet.DrawProto.LockPIcResp.Builder, com.liulin.auto.packet.DrawProto.LockPIcRespOrBuilder>(
                  lockPIcResp_,
                  getParentForChildren(),
                  isClean());
          lockPIcResp_ = null;
        }
        return lockPIcRespBuilder_;
      }
      
      // @@protoc_insertion_point(builder_scope:ReqMsg)
    }
    
    static {
      defaultInstance = new ReqMsg(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:ReqMsg)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_ReqMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_ReqMsg_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\treq.proto\032\ndraw.proto\"\227\001\n\006ReqMsg\022\021\n\tme" +
      "ssageId\030\001 \001(\005\022\016\n\006userId\030\002 \001(\003\022$\n\014uploadP" +
      "IcReq\030\221N \001(\0132\r.UploadPIcReq\022 \n\nlockPIcRe" +
      "q\030\223N \001(\0132\013.LockPIcReq\022\"\n\013lockPIcResp\030\224N " +
      "\001(\0132\014.LockPIcRespB%\n\026com.liulin.auto.pac" +
      "ketB\013ReqMsgProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_ReqMsg_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_ReqMsg_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_ReqMsg_descriptor,
              new String[] { "MessageId", "UserId", "UploadPIcReq", "LockPIcReq", "LockPIcResp", },
              ReqMsg.class,
              ReqMsg.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.liulin.auto.packet.DrawProto.getDescriptor(),
        }, assigner);
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}
