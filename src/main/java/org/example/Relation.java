package org.example;

import org.example.interfaces.IStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Relation implements IStream {
    enum Type {

        CHILD, // ребенок
        MARRIEDS, // супруги
    };

    private int id1, id2;
    private Type type;

    public Relation(int id1, int id2, Type type) {
        setID1toID2(id1, id2, type);
    }

    // Установка отношения id1 к id2 (type CHILD означает id1 - ребенок, id2 - родитель)

    void setID1toID2(int id1, int id2, Type type) {
        this.id1 = id1;
        this.id2 = id2;
        this.type = type;
    }

    int getID1() {
        return id1;
    }

    int getID2() {
        return id2;
    }

    Type getTypeID1toID2() {
        return type;
    }

    // Запись данных экземпляра в поток

    @Override
    public void save(DataOutputStream stream_out) throws IOException {
        stream_out.writeInt(id1);
        stream_out.writeInt(id2);
        int itype = 0;
        if (type == Type.CHILD)
            itype = 1;
        stream_out.writeInt(itype);
    }

    // Чтение данных экземпляра из потока

    @Override
    public void load(DataInputStream stream_in) throws IOException {
        int id1 = stream_in.readInt();
        int id2 = stream_in.readInt();
        int itype = stream_in.readInt();

        Type type = Type.MARRIEDS;
        if (itype == 1)
            type = Type.CHILD;

        this.id1 = id1;
        this.id2 = id2;
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj.getClass() != Relation.class)
            return false;
        return ((Relation) obj).id1 == id1 && ((Relation) obj).id2 == id2 && ((Relation) obj).type == type;
    }

    @Override
    public int hashCode() {
        return id1 + (id2 << 18) + type.ordinal() << 28;
    }
}