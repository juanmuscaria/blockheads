package com.juanmuscaria.blockheads.old.jna.enet;

import com.juanmuscaria.blockheads.old.jna.enet.structures.*;
import com.juanmuscaria.blockheads.old.jna.types.Size_t;
import com.juanmuscaria.blockheads.old.jna.types.Uint32_t;
import com.juanmuscaria.blockheads.old.jna.types.Uint8_t;
import com.sun.jna.*;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public interface Enet extends Library {
  String JNA_LIBRARY_NAME = (Platform.isWindows() ? "libenet.dll" : "bh_libenet.so");
  NativeLibrary JNA_NATIVE_LIB = NativeLibrary.getInstance(Enet.JNA_LIBRARY_NAME);
  Enet INSTANCE = Native.load(Enet.JNA_LIBRARY_NAME, Enet.class);
  int ENET_PROTOCOL_MINIMUM_MTU = 576;
  int ENET_PROTOCOL_MAXIMUM_MTU = 4096;
  int ENET_PROTOCOL_MAXIMUM_PACKET_COMMANDS = 32;
  int ENET_PROTOCOL_MINIMUM_WINDOW_SIZE = 4096;
  int ENET_PROTOCOL_MAXIMUM_WINDOW_SIZE = 65536;
  int ENET_PROTOCOL_MINIMUM_CHANNEL_COUNT = 1;
  int ENET_PROTOCOL_MAXIMUM_CHANNEL_COUNT = 255;
  int ENET_PROTOCOL_MAXIMUM_PEER_ID = 0xFFF;
  int ENET_PROTOCOL_MAXIMUM_FRAGMENT_COUNT = 1024 * 1024;
  int ENET_HOST_RECEIVE_BUFFER_SIZE = 256 * 1024;
  int ENET_HOST_SEND_BUFFER_SIZE = 256 * 1024;
  int ENET_HOST_BANDWIDTH_THROTTLE_INTERVAL = 1000;
  int ENET_HOST_DEFAULT_MTU = 1400;
  int ENET_HOST_DEFAULT_MAXIMUM_PACKET_SIZE = 32 * 1024 * 1024;
  int ENET_HOST_DEFAULT_MAXIMUM_WAITING_DATA = 32 * 1024 * 1024;
  int ENET_PEER_DEFAULT_ROUND_TRIP_TIME = 500;
  int ENET_PEER_DEFAULT_PACKET_THROTTLE = 32;
  int ENET_PEER_PACKET_THROTTLE_SCALE = 32;
  int ENET_PEER_PACKET_THROTTLE_COUNTER = 7;
  int ENET_PEER_PACKET_THROTTLE_ACCELERATION = 2;
  int ENET_PEER_PACKET_THROTTLE_DECELERATION = 2;
  int ENET_PEER_PACKET_THROTTLE_INTERVAL = 5000;
  int ENET_PEER_PACKET_LOSS_SCALE = (1 << 16);
  int ENET_PEER_PACKET_LOSS_INTERVAL = 10000;
  int ENET_PEER_WINDOW_SIZE_SCALE = 64 * 1024;
  int ENET_PEER_TIMEOUT_LIMIT = 32;
  int ENET_PEER_TIMEOUT_MINIMUM = 5000;
  int ENET_PEER_TIMEOUT_MAXIMUM = 30000;
  int ENET_PEER_PING_INTERVAL = 500;
  int ENET_PEER_UNSEQUENCED_WINDOWS = 64;
  int ENET_PEER_UNSEQUENCED_WINDOW_SIZE = 1024;
  int ENET_PEER_FREE_UNSEQUENCED_WINDOWS = 32;
  int ENET_PEER_RELIABLE_WINDOWS = 16;
  int ENET_PEER_RELIABLE_WINDOW_SIZE = 0x1000;
  int ENET_PEER_FREE_RELIABLE_WINDOWS = 8;
  /**
   * <i>native declaration : enet/enet.h</i>
   */
  int ENET_VERSION_MAJOR = 1;
  /**
   * <i>native declaration : enet/enet.h</i>
   */
  int ENET_VERSION_MINOR = 3;
  /**
   * <i>native declaration : enet/enet.h</i>
   */
  int ENET_VERSION_PATCH = 14;
  /**
   * <i>native declaration : enet/enet.h</i>
   */
  int ENET_VERSION = ((1) << 16) | ((3) << 8) | (14);
  /**
   * <i>native declaration : enet/enet.h</i>
   */
  int ENET_HOST_ANY = 0;
  /**
   * <i>native declaration : enet/enet.h</i>
   */
  long ENET_HOST_BROADCAST = 0xFFFFFFFFL;
  /**
   * <i>native declaration : enet/enet.h</i>
   */
  int ENET_PORT_ANY = 0;

  /**
   * Original signature : <code>void enet_list_clear(ENetList*)</code><br>
   * <i>native declaration : ./enet/list.h:15</i>
   */
  void enet_list_clear(ENetList ENetListPtr1);

  /**
   * Original signature : <code>ENetListIterator enet_list_insert(ENetListIterator, void*)</code><br>
   * <i>native declaration : ./enet/list.h:17</i>
   */
  ENetListNode enet_list_insert(ENetListNode ENetListIterator1, Pointer voidPtr1);

  /**
   * Original signature : <code>void* enet_list_remove(ENetListIterator)</code><br>
   * <i>native declaration : ./enet/list.h:18</i>
   */
  Pointer enet_list_remove(ENetListNode ENetListIterator1);

  /**
   * Original signature : <code>ENetListIterator enet_list_move(ENetListIterator, void*, void*)</code><br>
   * <i>native declaration : ./enet/list.h:19</i>
   */
  ENetListNode enet_list_move(ENetListNode ENetListIterator1, Pointer voidPtr1, Pointer voidPtr2);

  /**
   * Original signature : <code>size_t enet_list_size(ENetList*)</code><br>
   * <i>native declaration : ./enet/list.h:21</i>
   */
  Size_t enet_list_size(ENetList ENetListPtr1);

  /**
   * @defgroup callbacks ENet internal callbacks<br>
   * @{<br>
   * @ingroup private<br>
   * Original signature : <code>void* enet_malloc(size_t)</code><br>
   * <i>native declaration : ./enet/callbacks.h:13</i>
   */
  Pointer enet_malloc(Size_t size_t1);

  /**
   * Original signature : <code>void enet_free(void*)</code><br>
   * <i>native declaration : ./enet/callbacks.h:14</i>
   */
  void enet_free(Pointer voidPtr1);

  /**
   * Initializes ENet globally.  Must be called prior to using any functions in<br>
   * ENet.<br>
   *
   * @returns 0 on success, < 0 on failure<br>
   * Original signature : <code>int enet_initialize()</code><br>
   * <i>native declaration : enet/enet.h:446</i>
   */
  int enet_initialize();

  /**
   * Initializes ENet globally and supplies user-overridden callbacks. Must be called prior to using any functions in ENet. Do not use enet_initialize() if you use this variant. Make sure the ENetCallbacks structure is zeroed out so that any additional callbacks added in future versions will be properly ignored.<br>
   *
   * @param version the constant ENET_VERSION should be supplied so ENet knows which version of ENetCallbacks struct to use<br>
   * @param inits   user-overridden callbacks where any NULL callbacks will use ENet's defaults<br>
   * @returns 0 on success, < 0 on failure<br>
   * Original signature : <code>int enet_initialize_with_callbacks(ENetVersion, const ENetCallbacks*)</code><br>
   * <i>native declaration : enet/enet.h:455</i>
   */
  int enet_initialize_with_callbacks(int version, ENetCallbacks inits);

  /**
   * Shuts down ENet globally.  Should be called when a program that has<br>
   * initialized ENet exits.<br>
   * Original signature : <code>void enet_deinitialize()</code><br>
   * <i>native declaration : enet/enet.h:461</i>
   */
  void enet_deinitialize();

  /**
   * Gives the linked version of the ENet library.<br>
   *
   * @returns the version number<br>
   * Original signature : <code>ENetVersion enet_linked_version()</code><br>
   * <i>native declaration : enet/enet.h:467</i>
   */
  int enet_linked_version();

  /**
   * Returns the wall-time in milliseconds.  Its initial value is unspecified<br>
   * unless otherwise set.<br>
   * Original signature : <code>enet_uint32 enet_time_get()</code><br>
   * <i>native declaration : enet/enet.h:477</i>
   */
  int enet_time_get();

  /**
   * Sets the current wall-time in milliseconds.<br>
   * Original signature : <code>void enet_time_set(enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:481</i>
   */
  void enet_time_set(int enet_uint321);

  /**
   * @defgroup socket ENet socket functions<br>
   * @{<br> Original signature : <code>ENetSocket enet_socket_create(ENetSocketType)</code><br>
   * <i>native declaration : enet/enet.h:486</i>
   */
  ENetSocket enet_socket_create(int ENetSocketType1);

  /**
   * Original signature : <code>int enet_socket_bind(const ENetAddress*)</code><br>
   * <i>native declaration : enet/enet.h:487</i>
   */
  int enet_socket_bind(ENetAddress ENetAddressPtr1);

  /**
   * Original signature : <code>int enet_socket_get_address(ENetAddress*)</code><br>
   * <i>native declaration : enet/enet.h:488</i>
   */
  int enet_socket_get_address(ENetAddress ENetAddressPtr1);

  /**
   * Original signature : <code>int enet_socket_listen(int)</code><br>
   * <i>native declaration : enet/enet.h:489</i>
   */
  int enet_socket_listen(int int1);

  /**
   * Original signature : <code>ENetSocket enet_socket_accept(ENetSocket, ENetAddress*)</code><br>
   * <i>native declaration : enet/enet.h:490</i><br>
   *
   * @deprecated use the safer methods {@link #enet_socket_accept(ENetSocket, ENetAddress)} and {@link #enet_socket_accept(com.sun.jna.Pointer, ENetAddress)} instead
   */
  @Deprecated
  ENetSocket enet_socket_accept(Pointer ENetSocket1, ENetAddress ENetAddressPtr1);

  /**
   * Original signature : <code>ENetSocket enet_socket_accept(ENetSocket, ENetAddress*)</code><br>
   * <i>native declaration : enet/enet.h:490</i>
   */
  ENetSocket enet_socket_accept(ENetSocket ENetSocket1, ENetAddress ENetAddressPtr1);

  /**
   * Original signature : <code>int enet_socket_connect(const ENetAddress*)</code><br>
   * <i>native declaration : enet/enet.h:491</i>
   */
  int enet_socket_connect(ENetAddress ENetAddressPtr1);

  /**
   * Original signature : <code>int enet_socket_send(const ENetAddress*, const ENetBuffer*, size_t)</code><br>
   * <i>native declaration : enet/enet.h:492</i>
   */
  int enet_socket_send(ENetAddress ENetAddressPtr1, ENetBuffer ENetBufferPtr1, Size_t size_t1);

  /**
   * Original signature : <code>int enet_socket_receive(ENetAddress*, ENetBuffer*, size_t)</code><br>
   * <i>native declaration : enet/enet.h:493</i>
   */
  int enet_socket_receive(ENetAddress ENetAddressPtr1, ENetBuffer ENetBufferPtr1, Size_t size_t1);

  /**
   * Original signature : <code>int enet_socket_wait(enet_uint32*, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:494</i><br>
   *
   * @deprecated use the safer methods {@link #enet_socket_wait(IntBuffer, int)} and {@link #enet_socket_wait(com.sun.jna.ptr.IntByReference, int)} instead
   */
  @Deprecated
  int enet_socket_wait(IntByReference enet_uint32Ptr1, int enet_uint321);

  /**
   * Original signature : <code>int enet_socket_wait(enet_uint32*, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:494</i>
   */
  int enet_socket_wait(IntBuffer enet_uint32Ptr1, int enet_uint321);

  /**
   * Original signature : <code>int enet_socket_set_option(ENetSocketOption, int)</code><br>
   * <i>native declaration : enet/enet.h:495</i>
   */
  int enet_socket_set_option(int ENetSocketOption1, int int1);

  /**
   * Original signature : <code>int enet_socket_get_option(ENetSocketOption, int*)</code><br>
   * <i>native declaration : enet/enet.h:496</i><br>
   *
   * @deprecated use the safer methods {@link #enet_socket_get_option(int, IntBuffer)} and {@link #enet_socket_get_option(int, com.sun.jna.ptr.IntByReference)} instead
   */
  @Deprecated
  int enet_socket_get_option(int ENetSocketOption1, IntByReference intPtr1);

  /**
   * Original signature : <code>int enet_socket_get_option(ENetSocketOption, int*)</code><br>
   * <i>native declaration : enet/enet.h:496</i>
   */
  int enet_socket_get_option(int ENetSocketOption1, IntBuffer intPtr1);

  /**
   * Original signature : <code>int enet_socket_shutdown(ENetSocketShutdown)</code><br>
   * <i>native declaration : enet/enet.h:497</i>
   */
  int enet_socket_shutdown(int ENetSocketShutdown1);

  /**
   * Original signature : <code>void enet_socket_destroy()</code><br>
   * <i>native declaration : enet/enet.h:498</i>
   */
  void enet_socket_destroy();

  /**
   * Original signature : <code>int enet_socketset_select(ENetSocketSet*, ENetSocketSet*, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:499</i><br>
   *
   * @deprecated use the safer method {@link #enet_socketset_select(com.sun.jna.ptr.PointerByReference, com.sun.jna.ptr.PointerByReference, int)} instead
   */
  @Deprecated
  int enet_socketset_select(Pointer ENetSocketSetPtr1, Pointer ENetSocketSetPtr2, int enet_uint321);

  /**
   * Original signature : <code>int enet_socketset_select(ENetSocketSet*, ENetSocketSet*, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:499</i>
   */
  int enet_socketset_select(PointerByReference ENetSocketSetPtr1, PointerByReference ENetSocketSetPtr2, int enet_uint321);

  /**
   * Attempts to parse the printable form of the IP address in the parameter hostName<br>
   * and sets the host field in the address parameter if successful.<br>
   *
   * @param address  destination to store the parsed IP address<br>
   * @param hostName IP address to parse<br>
   * @retval 0 on success<br>
   * @retval < 0 on failure<br>
   * @returns the address of the given hostName in address on success<br>
   * Original signature : <code>int enet_address_set_host_ip(ENetAddress*, const char*)</code><br>
   * <i>native declaration : enet/enet.h:515</i><br>
   * @deprecated use the safer methods {@link #enet_address_set_host_ip(ENetAddress, String)} and {@link #enet_address_set_host_ip(ENetAddress, com.sun.jna.Pointer)} instead
   */
  @Deprecated
  int enet_address_set_host_ip(ENetAddress address, Pointer hostName);

  /**
   * Attempts to parse the printable form of the IP address in the parameter hostName<br>
   * and sets the host field in the address parameter if successful.<br>
   *
   * @param address  destination to store the parsed IP address<br>
   * @param hostName IP address to parse<br>
   * @retval 0 on success<br>
   * @retval < 0 on failure<br>
   * @returns the address of the given hostName in address on success<br>
   * Original signature : <code>int enet_address_set_host_ip(ENetAddress*, const char*)</code><br>
   * <i>native declaration : enet/enet.h:515</i>
   */
  int enet_address_set_host_ip(ENetAddress address, String hostName);

  /**
   * Attempts to resolve the host named by the parameter hostName and sets<br>
   * the host field in the address parameter if successful.<br>
   *
   * @param address  destination to store resolved address<br>
   * @param hostName host name to lookup<br>
   * @retval 0 on success<br>
   * @retval < 0 on failure<br>
   * @returns the address of the given hostName in address on success<br>
   * Original signature : <code>int enet_address_set_host(ENetAddress*, const char*)</code><br>
   * <i>native declaration : enet/enet.h:525</i><br>
   * @deprecated use the safer methods {@link #enet_address_set_host(ENetAddress, String)} and {@link #enet_address_set_host(ENetAddress, com.sun.jna.Pointer)} instead
   */
  @Deprecated
  int enet_address_set_host(ENetAddress address, Pointer hostName);

  /**
   * Attempts to resolve the host named by the parameter hostName and sets<br>
   * the host field in the address parameter if successful.<br>
   *
   * @param address  destination to store resolved address<br>
   * @param hostName host name to lookup<br>
   * @retval 0 on success<br>
   * @retval < 0 on failure<br>
   * @returns the address of the given hostName in address on success<br>
   * Original signature : <code>int enet_address_set_host(ENetAddress*, const char*)</code><br>
   * <i>native declaration : enet/enet.h:525</i>
   */
  int enet_address_set_host(ENetAddress address, String hostName);

  /**
   * Gives the printable form of the IP address specified in the address parameter.<br>
   *
   * @param address    address printed<br>
   * @param hostName   destination for name, must not be NULL<br>
   * @param nameLength maximum length of hostName.<br>
   * @returns the null-terminated name of the host in hostName on success<br>
   * @retval 0 on success<br>
   * @retval < 0 on failure<br>
   * Original signature : <code>int enet_address_get_host_ip(const ENetAddress*, char*, size_t)</code><br>
   * <i>native declaration : enet/enet.h:535</i><br>
   * @deprecated use the safer methods {@link #enet_address_get_host_ip(ENetAddress, ByteBuffer, Size_t)} and {@link #enet_address_get_host_ip(ENetAddress, com.sun.jna.Pointer, Size_t)} instead
   */
  @Deprecated
  int enet_address_get_host_ip(ENetAddress address, Pointer hostName, Size_t nameLength);

  /**
   * Gives the printable form of the IP address specified in the address parameter.<br>
   *
   * @param address    address printed<br>
   * @param hostName   destination for name, must not be NULL<br>
   * @param nameLength maximum length of hostName.<br>
   * @returns the null-terminated name of the host in hostName on success<br>
   * @retval 0 on success<br>
   * @retval < 0 on failure<br>
   * Original signature : <code>int enet_address_get_host_ip(const ENetAddress*, char*, size_t)</code><br>
   * <i>native declaration : enet/enet.h:535</i>
   */
  int enet_address_get_host_ip(ENetAddress address, ByteBuffer hostName, Size_t nameLength);

  /**
   * Attempts to do a reverse lookup of the host field in the address parameter.<br>
   *
   * @param address    address used for reverse lookup<br>
   * @param hostName   destination for name, must not be NULL<br>
   * @param nameLength maximum length of hostName.<br>
   * @returns the null-terminated name of the host in hostName on success<br>
   * @retval 0 on success<br>
   * @retval < 0 on failure<br>
   * Original signature : <code>int enet_address_get_host(const ENetAddress*, char*, size_t)</code><br>
   * <i>native declaration : enet/enet.h:545</i><br>
   * @deprecated use the safer methods {@link #enet_address_get_host(ENetAddress, ByteBuffer, Size_t)} and {@link #enet_address_get_host(ENetAddress, com.sun.jna.Pointer, Size_t)} instead
   */
  @Deprecated
  int enet_address_get_host(ENetAddress address, Pointer hostName, Size_t nameLength);

  /**
   * Attempts to do a reverse lookup of the host field in the address parameter.<br>
   *
   * @param address    address used for reverse lookup<br>
   * @param hostName   destination for name, must not be NULL<br>
   * @param nameLength maximum length of hostName.<br>
   * @returns the null-terminated name of the host in hostName on success<br>
   * @retval 0 on success<br>
   * @retval < 0 on failure<br>
   * Original signature : <code>int enet_address_get_host(const ENetAddress*, char*, size_t)</code><br>
   * <i>native declaration : enet/enet.h:545</i>
   */
  int enet_address_get_host(ENetAddress address, ByteBuffer hostName, Size_t nameLength);

  /**
   * @}<br> Original signature : <code>ENetPacket* enet_packet_create(const void*, size_t, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:549</i>
   */
  ENetPacket enet_packet_create(Pointer voidPtr1, Size_t size_t1, Uint32_t enet_uint321);

  /**
   * Original signature : <code>void enet_packet_destroy(ENetPacket*)</code><br>
   * <i>native declaration : enet/enet.h:550</i>
   */
  void enet_packet_destroy(ENetPacket ENetPacketPtr1);

  /**
   * Original signature : <code>int enet_packet_resize(ENetPacket*, size_t)</code><br>
   * <i>native declaration : enet/enet.h:551</i>
   */
  int enet_packet_resize(ENetPacket ENetPacketPtr1, Size_t size_t1);

  /**
   * Original signature : <code>enet_uint32 enet_crc32(const ENetBuffer*, size_t)</code><br>
   * <i>native declaration : enet/enet.h:552</i>
   */
  int enet_crc32(ENetBuffer ENetBufferPtr1, Size_t size_t1);

  /**
   * Original signature : <code>ENetHost* enet_host_create(const ENetAddress*, size_t, size_t, enet_uint32, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:554</i>
   */
  ENetHost enet_host_create(ENetAddress ENetAddressPtr1, Size_t size_t1, Size_t size_t2, Uint32_t enet_uint321, Uint32_t enet_uint322);

  /**
   * Original signature : <code>void enet_host_destroy(ENetHost*)</code><br>
   * <i>native declaration : enet/enet.h:555</i>
   */
  void enet_host_destroy(ENetHost ENetHostPtr1);

  /**
   * Original signature : <code>ENetPeer* enet_host_connect(ENetHost*, const ENetAddress*, size_t, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:556</i>
   */
  ENetPeer enet_host_connect(ENetHost ENetHostPtr1, ENetAddress ENetAddressPtr1, Size_t size_t1, Uint32_t enet_uint321);

  /**
   * Original signature : <code>int enet_host_check_events(ENetHost*, ENetEvent*)</code><br>
   * <i>native declaration : enet/enet.h:557</i>
   */
  int enet_host_check_events(ENetHost ENetHostPtr1, ENetEvent ENetEventPtr1);

  /**
   * Original signature : <code>int enet_host_service(ENetHost*, ENetEvent*, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:558</i>
   */
  int enet_host_service(ENetHost ENetHostPtr1, ENetEvent ENetEventPtr1, Uint32_t enet_uint321);

  /**
   * Original signature : <code>void enet_host_flush(ENetHost*)</code><br>
   * <i>native declaration : enet/enet.h:559</i>
   */
  void enet_host_flush(ENetHost ENetHostPtr1);

  /**
   * Original signature : <code>void enet_host_broadcast(ENetHost*, ENetPacket*)</code><br>
   * <i>native declaration : enet/enet.h:560</i>
   */
  void enet_host_broadcast(ENetHost ENetHostPtr1, ENetPacket ENetPacketPtr1);

  /**
   * Original signature : <code>void enet_host_compress(ENetHost*, const ENetCompressor*)</code><br>
   * <i>native declaration : enet/enet.h:561</i>
   */
  void enet_host_compress(ENetHost ENetHostPtr1, ENetCompressor ENetCompressorPtr1);

  /**
   * Original signature : <code>int enet_host_compress_with_range_coder(ENetHost*)</code><br>
   * <i>native declaration : enet/enet.h:562</i>
   */
  int enet_host_compress_with_range_coder(ENetHost host);

  /**
   * Original signature : <code>void enet_host_channel_limit(ENetHost*, size_t)</code><br>
   * <i>native declaration : enet/enet.h:563</i>
   */
  void enet_host_channel_limit(ENetHost ENetHostPtr1, Size_t size_t1);

  /**
   * Original signature : <code>void enet_host_bandwidth_limit(ENetHost*, enet_uint32, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:564</i>
   */
  void enet_host_bandwidth_limit(ENetHost ENetHostPtr1, Uint32_t enet_uint321, Uint32_t enet_uint322);

  /**
   * Original signature : <code>void enet_host_bandwidth_throttle(ENetHost*)</code><br>
   * <i>native declaration : enet/enet.h:565</i>
   */
  void enet_host_bandwidth_throttle(ENetHost ENetHostPtr1);

  /**
   * Original signature : <code>enet_uint32 enet_host_random_seed()</code><br>
   * <i>native declaration : enet/enet.h:566</i>
   */
  int enet_host_random_seed();

  /**
   * Original signature : <code>int enet_peer_send(ENetPeer*, ENetPacket*)</code><br>
   * <i>native declaration : enet/enet.h:568</i>
   */
  int enet_peer_send(ENetPeer ENetPeerPtr1, Uint8_t uint8, ENetPacket ENetPacketPtr1);

  /**
   * Original signature : <code>ENetPacket* enet_peer_receive(ENetPeer*, enet_uint8*)</code><br>
   * <i>native declaration : enet/enet.h:569</i><br>
   *
   * @deprecated use the safer methods {@link #enet_peer_receive(ENetPeer, ByteBuffer)} and {@link #enet_peer_receive(ENetPeer, com.sun.jna.Pointer)} instead
   */
  @Deprecated
  ENetPacket enet_peer_receive(ENetPeer ENetPeerPtr1, Pointer channelID);

  /**
   * Original signature : <code>ENetPacket* enet_peer_receive(ENetPeer*, enet_uint8*)</code><br>
   * <i>native declaration : enet/enet.h:569</i>
   */
  ENetPacket enet_peer_receive(ENetPeer ENetPeerPtr1, ByteBuffer channelID);

  /**
   * Original signature : <code>void enet_peer_ping(ENetPeer*)</code><br>
   * <i>native declaration : enet/enet.h:570</i>
   */
  void enet_peer_ping(ENetPeer ENetPeerPtr1);

  /**
   * Original signature : <code>void enet_peer_ping_interval(ENetPeer*, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:571</i>
   */
  void enet_peer_ping_interval(ENetPeer ENetPeerPtr1, Uint32_t enet_uint321);

  /**
   * Original signature : <code>void enet_peer_timeout(ENetPeer*, enet_uint32, enet_uint32, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:572</i>
   */
  void enet_peer_timeout(ENetPeer ENetPeerPtr1, Uint32_t enet_uint321, Uint32_t enet_uint322, Uint32_t enet_uint323);

  /**
   * Original signature : <code>void enet_peer_reset(ENetPeer*)</code><br>
   * <i>native declaration : enet/enet.h:573</i>
   */
  void enet_peer_reset(ENetPeer ENetPeerPtr1);

  /**
   * Original signature : <code>void enet_peer_disconnect(ENetPeer*, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:574</i>
   */
  void enet_peer_disconnect(ENetPeer ENetPeerPtr1, Uint32_t enet_uint321);

  /**
   * Original signature : <code>void enet_peer_disconnect_now(ENetPeer*, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:575</i>
   */
  void enet_peer_disconnect_now(ENetPeer ENetPeerPtr1, Uint32_t enet_uint321);

  /**
   * Original signature : <code>void enet_peer_disconnect_later(ENetPeer*, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:576</i>
   */
  void enet_peer_disconnect_later(ENetPeer ENetPeerPtr1, Uint32_t enet_uint321);

  /**
   * Original signature : <code>void enet_peer_throttle_configure(ENetPeer*, enet_uint32, enet_uint32, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:577</i>
   */
  void enet_peer_throttle_configure(ENetPeer ENetPeerPtr1, Uint32_t enet_uint321, Uint32_t enet_uint322, Uint32_t enet_uint323);

  /**
   * Original signature : <code>int enet_peer_throttle(ENetPeer*, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:578</i>
   */
  int enet_peer_throttle(ENetPeer ENetPeerPtr1, Uint32_t enet_uint321);

  /**
   * Original signature : <code>void enet_peer_reset_queues(ENetPeer*)</code><br>
   * <i>native declaration : enet/enet.h:579</i>
   */
  void enet_peer_reset_queues(ENetPeer ENetPeerPtr1);

  /**
   * Original signature : <code>void enet_peer_setup_outgoing_command(ENetPeer*, ENetOutgoingCommand*)</code><br>
   * <i>native declaration : enet/enet.h:580</i>
   */
  void enet_peer_setup_outgoing_command(ENetPeer ENetPeerPtr1, ENetOutgoingCommand ENetOutgoingCommandPtr1);

  /**
   * Original signature : <code>ENetOutgoingCommand* enet_peer_queue_outgoing_command(ENetPeer*, const ENetProtocol*, ENetPacket*, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:581</i>
   */
  ENetOutgoingCommand enet_peer_queue_outgoing_command(ENetPeer ENetPeerPtr1, ENetProtocol ENetProtocolPtr1, ENetPacket ENetPacketPtr1, int enet_uint321);

  /**
   * Original signature : <code>ENetIncomingCommand* enet_peer_queue_incoming_command(ENetPeer*, const ENetProtocol*, const void*, size_t, enet_uint32, enet_uint32)</code><br>
   * <i>native declaration : enet/enet.h:582</i>
   */
  ENetIncomingCommand enet_peer_queue_incoming_command(ENetPeer ENetPeerPtr1, ENetProtocol ENetProtocolPtr1, Pointer voidPtr1, Size_t size_t1, int enet_uint321, int enet_uint322);

  /**
   * Original signature : <code>ENetAcknowledgement* enet_peer_queue_acknowledgement(ENetPeer*, const ENetProtocol*)</code><br>
   * <i>native declaration : enet/enet.h:583</i>
   */
  ENetAcknowledgement enet_peer_queue_acknowledgement(ENetPeer ENetPeerPtr1, ENetProtocol ENetProtocolPtr1);

  /**
   * Original signature : <code>void enet_peer_dispatch_incoming_unreliable_commands(ENetPeer*, ENetChannel*)</code><br>
   * <i>native declaration : enet/enet.h:584</i>
   */
  void enet_peer_dispatch_incoming_unreliable_commands(ENetPeer ENetPeerPtr1, ENetChannel ENetChannelPtr1);

  /**
   * Original signature : <code>void enet_peer_dispatch_incoming_reliable_commands(ENetPeer*, ENetChannel*)</code><br>
   * <i>native declaration : enet/enet.h:585</i>
   */
  void enet_peer_dispatch_incoming_reliable_commands(ENetPeer ENetPeerPtr1, ENetChannel ENetChannelPtr1);

  /**
   * Original signature : <code>void enet_peer_on_connect(ENetPeer*)</code><br>
   * <i>native declaration : enet/enet.h:586</i>
   */
  void enet_peer_on_connect(ENetPeer ENetPeerPtr1);

  /**
   * Original signature : <code>void enet_peer_on_disconnect(ENetPeer*)</code><br>
   * <i>native declaration : enet/enet.h:587</i>
   */
  void enet_peer_on_disconnect(ENetPeer ENetPeerPtr1);

  /**
   * Original signature : <code>void* enet_range_coder_create()</code><br>
   * <i>native declaration : enet/enet.h:589</i>
   */
  Pointer enet_range_coder_create();

  /**
   * Original signature : <code>void enet_range_coder_destroy(void*)</code><br>
   * <i>native declaration : enet/enet.h:590</i>
   */
  void enet_range_coder_destroy(Pointer voidPtr1);

  /**
   * Original signature : <code>size_t enet_range_coder_compress(void*, const ENetBuffer*, size_t, size_t, enet_uint8*, size_t)</code><br>
   * <i>native declaration : enet/enet.h:591</i><br>
   *
   * @deprecated use the safer methods {@link #enet_range_coder_compress(com.sun.jna.Pointer, ENetBuffer, Size_t, Size_t, ByteBuffer, Size_t)} and {@link #enet_range_coder_compress(com.sun.jna.Pointer, ENetBuffer, Size_t, Size_t, com.sun.jna.Pointer, Size_t)} instead
   */
  @Deprecated
  Size_t enet_range_coder_compress(Pointer voidPtr1, ENetBuffer ENetBufferPtr1, Size_t size_t1, Size_t size_t2, Pointer enet_uint8Ptr1, Size_t size_t3);

  /**
   * Original signature : <code>size_t enet_range_coder_compress(void*, const ENetBuffer*, size_t, size_t, enet_uint8*, size_t)</code><br>
   * <i>native declaration : enet/enet.h:591</i>
   */
  Size_t enet_range_coder_compress(Pointer voidPtr1, ENetBuffer ENetBufferPtr1, Size_t size_t1, Size_t size_t2, ByteBuffer enet_uint8Ptr1, Size_t size_t3);

  /**
   * Original signature : <code>size_t enet_range_coder_decompress(void*, const enet_uint8*, size_t, enet_uint8*, size_t)</code><br>
   * <i>native declaration : enet/enet.h:592</i><br>
   *
   * @deprecated use the safer methods {@link #enet_range_coder_decompress(com.sun.jna.Pointer, ByteBuffer, Size_t, ByteBuffer, Size_t)} and {@link #enet_range_coder_decompress(com.sun.jna.Pointer, com.sun.jna.Pointer, Size_t, com.sun.jna.Pointer, Size_t)} instead
   */
  @Deprecated
  Size_t enet_range_coder_decompress(Pointer voidPtr1, Pointer enet_uint8Ptr1, Size_t size_t1, Pointer enet_uint8Ptr2, Size_t size_t2);

  /**
   * Original signature : <code>size_t enet_range_coder_decompress(void*, const enet_uint8*, size_t, enet_uint8*, size_t)</code><br>
   * <i>native declaration : enet/enet.h:592</i>
   */
  Size_t enet_range_coder_decompress(Pointer voidPtr1, ByteBuffer enet_uint8Ptr1, Size_t size_t1, ByteBuffer enet_uint8Ptr2, Size_t size_t2);

  /**
   * Original signature : <code>size_t enet_protocol_command_size()</code><br>
   * <i>native declaration : enet/enet.h:594</i>
   */
  Size_t enet_protocol_command_size();

  interface ENetProtocolCommand {
    int ENET_PROTOCOL_COMMAND_NONE = 0;
    int ENET_PROTOCOL_COMMAND_ACKNOWLEDGE = 1;
    int ENET_PROTOCOL_COMMAND_CONNECT = 2;
    int ENET_PROTOCOL_COMMAND_VERIFY_CONNECT = 3;
    int ENET_PROTOCOL_COMMAND_DISCONNECT = 4;
    int ENET_PROTOCOL_COMMAND_PING = 5;
    int ENET_PROTOCOL_COMMAND_SEND_RELIABLE = 6;
    int ENET_PROTOCOL_COMMAND_SEND_UNRELIABLE = 7;
    int ENET_PROTOCOL_COMMAND_SEND_FRAGMENT = 8;
    int ENET_PROTOCOL_COMMAND_SEND_UNSEQUENCED = 9;
    int ENET_PROTOCOL_COMMAND_BANDWIDTH_LIMIT = 10;
    int ENET_PROTOCOL_COMMAND_THROTTLE_CONFIGURE = 11;
    int ENET_PROTOCOL_COMMAND_SEND_UNRELIABLE_FRAGMENT = 12;
    int ENET_PROTOCOL_COMMAND_COUNT = 13;
    int ENET_PROTOCOL_COMMAND_MASK = 0x0F;
  }

  /**
   * enum values
   */
  interface ENetProtocolFlag {
    /**
     * <i>native declaration : ./enet/protocol.h:37</i>
     */
    int ENET_PROTOCOL_COMMAND_FLAG_ACKNOWLEDGE = (1 << 7);
    /**
     * <i>native declaration : ./enet/protocol.h:38</i>
     */
    int ENET_PROTOCOL_COMMAND_FLAG_UNSEQUENCED = (1 << 6);
    /**
     * <i>native declaration : ./enet/protocol.h:40</i>
     */
    int ENET_PROTOCOL_HEADER_FLAG_COMPRESSED = (1 << 14);
    /**
     * <i>native declaration : ./enet/protocol.h:41</i>
     */
    int ENET_PROTOCOL_HEADER_FLAG_SENT_TIME = (1 << 15);
    /**
     * <i>native declaration : ./enet/protocol.h:42</i>
     */
    int ENET_PROTOCOL_HEADER_FLAG_MASK = ENetProtocolFlag.ENET_PROTOCOL_HEADER_FLAG_COMPRESSED | ENetProtocolFlag.ENET_PROTOCOL_HEADER_FLAG_SENT_TIME;
    /**
     * <i>native declaration : ./enet/protocol.h:44</i>
     */
    int ENET_PROTOCOL_HEADER_SESSION_MASK = (3 << 12);
    /**
     * <i>native declaration : ./enet/protocol.h:45</i>
     */
    int ENET_PROTOCOL_HEADER_SESSION_SHIFT = 12;
  }

  /**
   * <i>native declaration : enet/enet.h</i><br>
   * enum values
   */
  interface ENetSocketType {
    /**
     * <i>native declaration : enet/enet.h:37</i>
     */
    int ENET_SOCKET_TYPE_STREAM = 1;
    /**
     * <i>native declaration : enet/enet.h:38</i>
     */
    int ENET_SOCKET_TYPE_DATAGRAM = 2;
  }

  /**
   * <i>native declaration : enet/enet.h</i><br>
   * enum values
   */
  interface ENetSocketWait {
    /**
     * <i>native declaration : enet/enet.h:43</i>
     */
    int ENET_SOCKET_WAIT_NONE = 0;
    /**
     * <i>native declaration : enet/enet.h:44</i>
     */
    int ENET_SOCKET_WAIT_SEND = (1 << 0);
    /**
     * <i>native declaration : enet/enet.h:45</i>
     */
    int ENET_SOCKET_WAIT_RECEIVE = (1 << 1);
    /**
     * <i>native declaration : enet/enet.h:46</i>
     */
    int ENET_SOCKET_WAIT_INTERRUPT = (1 << 2);
  }

  /**
   * <i>native declaration : enet/enet.h</i><br>
   * enum values
   */
  interface ENetSocketOption {
    /**
     * <i>native declaration : enet/enet.h:51</i>
     */
    int ENET_SOCKOPT_NONBLOCK = 1;
    /**
     * <i>native declaration : enet/enet.h:52</i>
     */
    int ENET_SOCKOPT_BROADCAST = 2;
    /**
     * <i>native declaration : enet/enet.h:53</i>
     */
    int ENET_SOCKOPT_RCVBUF = 3;
    /**
     * <i>native declaration : enet/enet.h:54</i>
     */
    int ENET_SOCKOPT_SNDBUF = 4;
    /**
     * <i>native declaration : enet/enet.h:55</i>
     */
    int ENET_SOCKOPT_REUSEADDR = 5;
    /**
     * <i>native declaration : enet/enet.h:56</i>
     */
    int ENET_SOCKOPT_RCVTIMEO = 6;
    /**
     * <i>native declaration : enet/enet.h:57</i>
     */
    int ENET_SOCKOPT_SNDTIMEO = 7;
    /**
     * <i>native declaration : enet/enet.h:58</i>
     */
    int ENET_SOCKOPT_ERROR = 8;
    /**
     * <i>native declaration : enet/enet.h:59</i>
     */
    int ENET_SOCKOPT_NODELAY = 9;
  }

  /**
   * <i>native declaration : enet/enet.h</i><br>
   * enum values
   */
  interface ENetSocketShutdown {
    /**
     * <i>native declaration : enet/enet.h:64</i>
     */
    int ENET_SOCKET_SHUTDOWN_READ = 0;
    /**
     * <i>native declaration : enet/enet.h:65</i>
     */
    int ENET_SOCKET_SHUTDOWN_WRITE = 1;
    /**
     * <i>native declaration : enet/enet.h:66</i>
     */
    int ENET_SOCKET_SHUTDOWN_READ_WRITE = 2;
  }

  /**
   * <i>native declaration : enet/enet.h</i><br>
   * enum values
   */
  interface ENetPacketFlag {
    /**
     * <i>native declaration : enet/enet.h:102</i>
     */
    int ENET_PACKET_FLAG_RELIABLE = (1 << 0);
    /**
     * <i>native declaration : enet/enet.h:106</i>
     */
    int ENET_PACKET_FLAG_UNSEQUENCED = (1 << 1);
    /**
     * <i>native declaration : enet/enet.h:108</i>
     */
    int ENET_PACKET_FLAG_NO_ALLOCATE = (1 << 2);
    /**
     * <i>native declaration : enet/enet.h:111</i>
     */
    int ENET_PACKET_FLAG_UNRELIABLE_FRAGMENT = (1 << 3);
    /**
     * <i>native declaration : enet/enet.h:114</i>
     */
    int ENET_PACKET_FLAG_SENT = (1 << 8);
  }

  /**
   * <i>native declaration : enet/enet.h</i><br>
   * enum values
   */
  interface ENetPeerState {
    /**
     * <i>native declaration : enet/enet.h:188</i>
     */
    int ENET_PEER_STATE_DISCONNECTED = 0;
    /**
     * <i>native declaration : enet/enet.h:189</i>
     */
    int ENET_PEER_STATE_CONNECTING = 1;
    /**
     * <i>native declaration : enet/enet.h:190</i>
     */
    int ENET_PEER_STATE_ACKNOWLEDGING_CONNECT = 2;
    /**
     * <i>native declaration : enet/enet.h:191</i>
     */
    int ENET_PEER_STATE_CONNECTION_PENDING = 3;
    /**
     * <i>native declaration : enet/enet.h:192</i>
     */
    int ENET_PEER_STATE_CONNECTION_SUCCEEDED = 4;
    /**
     * <i>native declaration : enet/enet.h:193</i>
     */
    int ENET_PEER_STATE_CONNECTED = 5;
    /**
     * <i>native declaration : enet/enet.h:194</i>
     */
    int ENET_PEER_STATE_DISCONNECT_LATER = 6;
    /**
     * <i>native declaration : enet/enet.h:195</i>
     */
    int ENET_PEER_STATE_DISCONNECTING = 7;
    /**
     * <i>native declaration : enet/enet.h:196</i>
     */
    int ENET_PEER_STATE_ACKNOWLEDGING_DISCONNECT = 8;
    /**
     * <i>native declaration : enet/enet.h:197</i>
     */
    int ENET_PEER_STATE_ZOMBIE = 9;
  }

  /**
   * <i>native declaration : enet/enet.h</i><br>
   * enum values
   */
  interface ENetEventType {
    /**
     * <i>native declaration : enet/enet.h:398</i>
     */
    int ENET_EVENT_TYPE_NONE = 0;
    /**
     * <i>native declaration : enet/enet.h:403</i>
     */
    int ENET_EVENT_TYPE_CONNECT = 1;
    /**
     * <i>native declaration : enet/enet.h:412</i>
     */
    int ENET_EVENT_TYPE_DISCONNECT = 2;
    /**
     * <i>native declaration : enet/enet.h:420</i>
     */
    int ENET_EVENT_TYPE_RECEIVE = 3;
  }

  /**
   * <i>native declaration : enet/enet.h</i>
   */
  interface ENetPacketFreeCallback extends Callback {
    void apply(ENetPacket _ENetPacketPtr1);
  }

  /**
   * <i>native declaration : enet/enet.h</i>
   */
  interface ENetChecksumCallback extends Callback {
    int apply(ENetBuffer buffers, Size_t bufferCount);
  }

  /**
   * <i>native declaration : enet/enet.h</i>
   */
  interface ENetInterceptCallback extends Callback {
    int apply(ENetHost host, ENetEvent event);
  }

  class ENetSocket extends PointerType {
    public ENetSocket(Pointer address) {
      super(address);
    }

    public ENetSocket() {
      super();
    }
  }

  class ENetSocketSet extends PointerType {
    public ENetSocketSet(Pointer address) {
      super(address);
    }

    public ENetSocketSet() {
      super();
    }
  }
}
