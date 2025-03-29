/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.networking;

import java.net.InetAddress;
import java.net.UnknownHostException;
import pisi.unitedmeows.yystal.clazz.prop;

public class IPAddress {
    public static final IPAddress LOOPBACK = new IPAddress("127.0.0.1");
    public static final IPAddress ANY = new IPAddress("0.0.0.0");
    private final String address;
    public prop<InetAddress> inetAddress = new prop<InetAddress>(null){

        @Override
        public InetAddress get() {
            if (this.value == null) {
                try {
                    this.value = InetAddress.getByName(IPAddress.this.getAddress());
                }
                catch (UnknownHostException unknownHostException) {
                    // empty catch block
                }
            }
            return (InetAddress)this.value;
        }

        @Override
        @Deprecated
        public void set(InetAddress newValue) {
        }
    };

    protected IPAddress(String _address) {
        this.address = _address;
    }

    public static IPAddress from(String address) {
        return new IPAddress(address);
    }

    public static IPAddress parse(String address) {
        try {
            return new IPAddress(InetAddress.getByName(address).getHostAddress());
        }
        catch (UnknownHostException e) {
            return null;
        }
    }

    public String getAddress() {
        return this.address;
    }
}

