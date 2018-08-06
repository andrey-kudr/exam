// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: test.proto

package com.adven.concordion.extensions.exam.utils.protobuf;

public final class TestEntity {
  private TestEntity() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface EntityOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.adven.concordion.extensions.Entity)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string name = 1;</code>
     */
    java.lang.String getName();
    /**
     * <code>string name = 1;</code>
     */
    com.google.protobuf.ByteString
        getNameBytes();

    /**
     * <code>int32 number = 2;</code>
     */
    int getNumber();
  }
  /**
   * Protobuf type {@code com.adven.concordion.extensions.Entity}
   */
  public  static final class Entity extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.adven.concordion.extensions.Entity)
      EntityOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Entity.newBuilder() to construct.
    private Entity(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Entity() {
      name_ = "";
      number_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Entity(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              name_ = s;
              break;
            }
            case 16: {

              number_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.internal_static_com_adven_concordion_extensions_Entity_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.internal_static_com_adven_concordion_extensions_Entity_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity.class, com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity.Builder.class);
    }

    public static final int NAME_FIELD_NUMBER = 1;
    private volatile java.lang.Object name_;
    /**
     * <code>string name = 1;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      }
    }
    /**
     * <code>string name = 1;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int NUMBER_FIELD_NUMBER = 2;
    private int number_;
    /**
     * <code>int32 number = 2;</code>
     */
    public int getNumber() {
      return number_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, name_);
      }
      if (number_ != 0) {
        output.writeInt32(2, number_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, name_);
      }
      if (number_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, number_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity)) {
        return super.equals(obj);
      }
      com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity other = (com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity) obj;

      boolean result = true;
      result = result && getName()
          .equals(other.getName());
      result = result && (getNumber()
          == other.getNumber());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
      hash = (37 * hash) + NUMBER_FIELD_NUMBER;
      hash = (53 * hash) + getNumber();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.adven.concordion.extensions.Entity}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.adven.concordion.extensions.Entity)
        com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.EntityOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.internal_static_com_adven_concordion_extensions_Entity_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.internal_static_com_adven_concordion_extensions_Entity_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity.class, com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity.Builder.class);
      }

      // Construct using com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        name_ = "";

        number_ = 0;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.internal_static_com_adven_concordion_extensions_Entity_descriptor;
      }

      public com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity getDefaultInstanceForType() {
        return com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity.getDefaultInstance();
      }

      public com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity build() {
        com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity buildPartial() {
        com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity result = new com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity(this);
        result.name_ = name_;
        result.number_ = number_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity) {
          return mergeFrom((com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity other) {
        if (other == com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity.getDefaultInstance()) return this;
        if (!other.getName().isEmpty()) {
          name_ = other.name_;
          onChanged();
        }
        if (other.getNumber() != 0) {
          setNumber(other.getNumber());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object name_ = "";
      /**
       * <code>string name = 1;</code>
       */
      public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string name = 1;</code>
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string name = 1;</code>
       */
      public Builder setName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string name = 1;</code>
       */
      public Builder clearName() {
        
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <code>string name = 1;</code>
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        name_ = value;
        onChanged();
        return this;
      }

      private int number_ ;
      /**
       * <code>int32 number = 2;</code>
       */
      public int getNumber() {
        return number_;
      }
      /**
       * <code>int32 number = 2;</code>
       */
      public Builder setNumber(int value) {
        
        number_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 number = 2;</code>
       */
      public Builder clearNumber() {
        
        number_ = 0;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.adven.concordion.extensions.Entity)
    }

    // @@protoc_insertion_point(class_scope:com.adven.concordion.extensions.Entity)
    private static final com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity();
    }

    public static com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Entity>
        PARSER = new com.google.protobuf.AbstractParser<Entity>() {
      public Entity parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Entity(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Entity> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Entity> getParserForType() {
      return PARSER;
    }

    public com.adven.concordion.extensions.exam.utils.protobuf.TestEntity.Entity getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_adven_concordion_extensions_Entity_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_adven_concordion_extensions_Entity_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\ntest.proto\022\037com.adven.concordion.exten" +
      "sions\"&\n\006Entity\022\014\n\004name\030\001 \001(\t\022\016\n\006number\030" +
      "\002 \001(\005BA\n3com.adven.concordion.extensions" +
      ".exam.utils.protobufB\nTestEntityb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_adven_concordion_extensions_Entity_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_adven_concordion_extensions_Entity_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_adven_concordion_extensions_Entity_descriptor,
        new java.lang.String[] { "Name", "Number", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}