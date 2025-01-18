package org.signaling.signaling_server.entity.callroommember;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCallRoomMemberEntity is a Querydsl query type for CallRoomMemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCallRoomMemberEntity extends EntityPathBase<CallRoomMemberEntity> {

    private static final long serialVersionUID = -43974610L;

    public static final QCallRoomMemberEntity callRoomMemberEntity = new QCallRoomMemberEntity("callRoomMemberEntity");

    public final NumberPath<Long> callRoomId = createNumber("callRoomId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final EnumPath<org.signaling.signaling_server.entity.callroommember.enums.CallRoomMemberRole> role = createEnum("role", org.signaling.signaling_server.entity.callroommember.enums.CallRoomMemberRole.class);

    public QCallRoomMemberEntity(String variable) {
        super(CallRoomMemberEntity.class, forVariable(variable));
    }

    public QCallRoomMemberEntity(Path<? extends CallRoomMemberEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCallRoomMemberEntity(PathMetadata metadata) {
        super(CallRoomMemberEntity.class, metadata);
    }

}

