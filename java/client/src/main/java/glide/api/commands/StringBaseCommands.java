/** Copyright Valkey GLIDE Project Contributors - SPDX Identifier: Apache-2.0 */
package glide.api.commands;

import glide.api.models.GlideString;
import glide.api.models.commands.GetExOptions;
import glide.api.models.commands.SetOptions;
import glide.api.models.commands.SetOptions.ConditionalSet;
import glide.api.models.commands.SetOptions.SetOptionsBuilder;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Supports commands for the "String Commands" group for standalone and cluster clients.
 *
 * @see <a href="https://valkey.io/commands/?group=string">String Commands</a>
 */
public interface StringBaseCommands {

    /** Valkey API keyword used to indicate that the length of the lcs should be returned. */
    public static final String LEN_VALKEY_API = "LEN";

    /** <code>IDX</code> option string to include in the <code>LCS</code> command. */
    public static final String IDX_COMMAND_STRING = "IDX";

    /** <code>MINMATCHLEN</code> option string to include in the <code>LCS</code> command. */
    public static final String MINMATCHLEN_COMMAND_STRING = "MINMATCHLEN";

    /** <code>WITHMATCHLEN</code> option string to include in the <code>LCS</code> command. */
    public static final String WITHMATCHLEN_COMMAND_STRING = "WITHMATCHLEN";

    /** Key for LCS matches result. */
    public static final String LCS_MATCHES_RESULT_KEY = "matches";

    /**
     * Gets the value associated with the given <code>key</code>, or <code>null</code> if no such
     * value exists.
     *
     * @see <a href="https://valkey.io/commands/get/">valkey.io</a> for details.
     * @param key The <code>key</code> to retrieve from the database.
     * @return If <code>key</code> exists, returns the <code>value</code> of <code>key</code> as a
     *     <code>String</code>. Otherwise, return <code>null</code>.
     * @example
     *     <pre>{@code
     * String value = client.get("key").get();
     * assert value.equals("value");
     *
     * String value = client.get("non_existing_key").get();
     * assert value.equals(null);
     * }</pre>
     */
    CompletableFuture<String> get(String key);

    /**
     * Gets the value associated with the given <code>key</code>, or <code>null</code> if no such
     * value exists.
     *
     * @see <a href="https://valkey.io/commands/get/">valkey.io</a> for details.
     * @param key The <code>key</code> to retrieve from the database.
     * @return If <code>key</code> exists, returns the <code>value</code> of <code>key</code> as a
     *     <code>String</code>. Otherwise, return <code>null</code>.
     * @example
     *     <pre>{@code
     * GlideString value = client.get(gs("key")).get();
     * assert Arrays.equals(value.getString(), "value");
     *
     * String value = client.get("non_existing_key").get();
     * assert value.equals(null);
     * }</pre>
     */
    CompletableFuture<GlideString> get(GlideString key);

    /**
     * Gets a string value associated with the given <code>key</code> and deletes the key.
     *
     * @see <a href="https://valkey.io/commands/getdel/">valkey.io</a> for details.
     * @param key The <code>key</code> to retrieve from the database.
     * @return If <code>key</code> exists, returns the <code>value</code> of <code>key</code>.
     *     Otherwise, return <code>null</code>.
     * @example
     *     <pre>{@code
     * String value = client.getdel("key").get();
     * assert value.equals("value");
     *
     * String value = client.getdel("key").get();
     * assert value.equals(null);
     * }</pre>
     */
    CompletableFuture<String> getdel(String key);

    /**
     * Gets a string value associated with the given <code>key</code> and deletes the key.
     *
     * @see <a href="https://valkey.io/commands/getdel/">valkey.io</a> for details.
     * @param key The <code>key</code> to retrieve from the database.
     * @return If <code>key</code> exists, returns the <code>value</code> of <code>key</code>.
     *     Otherwise, return <code>null</code>.
     * @example
     *     <pre>{@code
     * GlideString value = client.getdel(gs("key")).get();
     * assert value.getString().equals("value");
     *
     * GlideString value = client.getdel(gs("key")).get();
     * assert value.equals(null);
     * }</pre>
     */
    CompletableFuture<GlideString> getdel(GlideString key);

    /**
     * Gets the value associated with the given <code>key</code>.
     *
     * @since Valkey 6.2.0.
     * @see <a href="https://valkey.io/commands/getex/">valkey.io</a> for details.
     * @param key The <code>key</code> to retrieve from the database.
     * @return If <code>key</code> exists, return the <code>value</code> of the <code>key</code>.
     *     Otherwise, return <code>null</code>.
     * @example
     *     <pre>{@code
     * String value = client.getex("key").get();
     * assert value.equals("value");
     * }</pre>
     */
    CompletableFuture<String> getex(String key);

    /**
     * Gets the value associated with the given <code>key</code>.
     *
     * @since Valkey 6.2.0.
     * @see <a href="https://valkey.io/commands/getex/">valkey.io</a> for details.
     * @param key The <code>key</code> to retrieve from the database.
     * @return If <code>key</code> exists, return the <code>value</code> of the <code>key</code>.
     *     Otherwise, return <code>null</code>.
     * @example
     *     <pre>{@code
     * GlideString value = client.getex(gs("key")).get();
     * assert value.equals(gs("value"));
     * }</pre>
     */
    CompletableFuture<GlideString> getex(GlideString key);

    /**
     * Gets the value associated with the given <code>key</code>.
     *
     * @since Valkey 6.2.0.
     * @see <a href="https://valkey.io/commands/getex/">valkey.io</a> for details.
     * @param key The <code>key</code> to retrieve from the database.
     * @param options The {@link GetExOptions} options.
     * @return If <code>key</code> exists, return the <code>value</code> of the <code>key</code>.
     *     Otherwise, return <code>null</code>.
     * @example
     *     <pre>{@code
     * String response = client.set("key", "value").get();
     * assert response.equals(OK);
     * String value = client.getex("key", GetExOptions.Seconds(10L)).get();
     * assert value.equals("value");
     * }</pre>
     */
    CompletableFuture<String> getex(String key, GetExOptions options);

    /**
     * Gets the value associated with the given <code>key</code>.
     *
     * @since Valkey 6.2.0.
     * @see <a href="https://valkey.io/commands/getex/">valkey.io</a> for details.
     * @param key The <code>key</code> to retrieve from the database.
     * @param options The {@link GetExOptions} options.
     * @return If <code>key</code> exists, return the <code>value</code> of the <code>key</code>.
     *     Otherwise, return <code>null</code>.
     * @example
     *     <pre>{@code
     * String response = client.set(gs("key"), gs("value").get();
     * assert response.equals(OK);
     * GlideString value = client.getex(gs("key"), GetExOptions.Seconds(10L)).get();
     * assert value.equals(gs("value"));
     * }</pre>
     */
    CompletableFuture<GlideString> getex(GlideString key, GetExOptions options);

    /**
     * Sets the given <code>key</code> with the given value.
     *
     * @see <a href="https://valkey.io/commands/set/">valkey.io</a> for details.
     * @param key The <code>key</code> to store.
     * @param value The value to store with the given <code>key</code>.
     * @return A simple <code>"OK"</code> response.
     * @example
     *     <pre>{@code
     * String value = client.set("key", "value").get();
     * assert value.equals("OK");
     * }</pre>
     */
    CompletableFuture<String> set(String key, String value);

    /**
     * Sets the given <code>key</code> with the given value.
     *
     * @see <a href="https://valkey.io/commands/set/">valkey.io</a> for details.
     * @param key The <code>key</code> to store.
     * @param value The value to store with the given <code>key</code>.
     * @return A simple <code>"OK"</code> response.
     * @example
     *     <pre>{@code
     * GlideString value = client.set(gs("key"), gs("value")).get();
     * assert value.getString().equals("OK");
     * }</pre>
     */
    CompletableFuture<String> set(GlideString key, GlideString value);

    /**
     * Sets the given key with the given value. Return value is dependent on the passed options.
     *
     * @see <a href="https://valkey.io/commands/set/">valkey.io</a> for details.
     * @param key The key to store.
     * @param value The value to store with the given key.
     * @param options The Set options.
     * @return If the value is successfully set, return <code>"OK"</code>. If value isn't set because
     *     of {@link ConditionalSet#ONLY_IF_EXISTS} or {@link ConditionalSet#ONLY_IF_DOES_NOT_EXIST}
     *     or {@link ConditionalSet#ONLY_IF_EQUAL} conditions, return <code>null</code>. If {@link
     *     SetOptionsBuilder#returnOldValue(boolean)} is set, return the old value as a <code>String
     *     </code>.
     * @example
     *     <pre>{@code
     * SetOptions options = SetOptions.builder().conditionalSetOnlyIfExists().expiry(Seconds(5L)).build();
     * String value = client.set("key", "value", options).get();
     * assert value.equals("OK");
     * }</pre>
     *     <pre>{@code
     * client.set("key", "value").get();
     * SetOptions options = SetOptions.builder().conditionalSetIfEqualTo("value").build();
     * String value = client.set("key", "newValue", options).get();
     * assert value.equals("OK");
     * }</pre>
     */
    CompletableFuture<String> set(String key, String value, SetOptions options);

    /**
     * Sets the given key with the given value. Return value is dependent on the passed options.
     *
     * @see <a href="https://valkey.io/commands/set/">valkey.io</a> for details.
     * @param key The key to store.
     * @param value The value to store with the given key.
     * @param options The Set options.
     * @return If the value is successfully set, return <code>"OK"</code>. If value isn't set because
     *     of {@link ConditionalSet#ONLY_IF_EXISTS} or {@link ConditionalSet#ONLY_IF_DOES_NOT_EXIST}
     *     or {@link ConditionalSet#ONLY_IF_EQUAL} conditions, return <code>null</code>. If {@link
     *     SetOptionsBuilder#returnOldValue(boolean)} is set, return the old value as a <code>String
     *     </code>.
     * @example
     *     <pre>{@code
     * SetOptions options = SetOptions.builder().conditionalSet(ONLY_IF_EXISTS).expiry(Seconds(5L)).build();
     * String value = client.set("key".getBytes(), "value".getBytes(), options).get();
     * assert value.equals("OK");
     * }</pre>
     */
    CompletableFuture<String> set(GlideString key, GlideString value, SetOptions options);

    /**
     * Retrieves the values of multiple <code>keys</code>.
     *
     * @apiNote In cluster mode, if keys in <code>keys</code> map to different hash slots, the command
     *     will be split across these slots and executed separately for each. This means the command
     *     is atomic only at the slot level. If one or more slot-specific requests fail, the entire
     *     call will return the first encountered error, even though some requests may have succeeded
     *     while others did not. If this behavior impacts your application logic, consider splitting
     *     the request into sub-requests per slot to ensure atomicity.
     * @see <a href="https://valkey.io/commands/mget/">valkey.io</a> for details.
     * @param keys A list of keys to retrieve values for.
     * @return An array of values corresponding to the provided <code>keys</code>.<br>
     *     If a <code>key</code>is not found, its corresponding value in the list will be <code>null
     *     </code>.
     * @example
     *     <pre>{@code
     * String values = client.mget(new String[] {"key1", "key2"}).get();
     * assert values.equals(new String[] {"value1", "value2"});
     * }</pre>
     */
    CompletableFuture<String[]> mget(String[] keys);

    /**
     * Retrieves the values of multiple <code>keys</code>.
     *
     * @apiNote In cluster mode, if keys in <code>keys</code> map to different hash slots, the command
     *     will be split across these slots and executed separately for each. This means the command
     *     is atomic only at the slot level. If one or more slot-specific requests fail, the entire
     *     call will return the first encountered error, even though some requests may have succeeded
     *     while others did not. If this behavior impacts your application logic, consider splitting
     *     the request into sub-requests per slot to ensure atomicity.
     * @see <a href="https://valkey.io/commands/mget/">valkey.io</a> for details.
     * @param keys A list of keys to retrieve values for.
     * @return An array of values corresponding to the provided <code>keys</code>.<br>
     *     If a <code>key</code>is not found, its corresponding value in the list will be <code>null
     *     </code>.
     * @example
     *     <pre>{@code
     * GlideString[] values = client.mget(new GlideString[] {"key1", "key2"}).get();
     * assert values.equals(new GlideString[] {"value1", "value2"});
     * }</pre>
     */
    CompletableFuture<GlideString[]> mget(GlideString[] keys);

    /**
     * Sets multiple keys to multiple values in a single operation.
     *
     * @apiNote In cluster mode, if keys in <code>keyValueMap</code> map to different hash slots, the
     *     command will be split across these slots and executed separately for each. This means the
     *     command is atomic only at the slot level. If one or more slot-specific requests fail, the
     *     entire call will return the first encountered error, even though some requests may have
     *     succeeded while others did not. If this behavior impacts your application logic, consider
     *     splitting the request into sub-requests per slot to ensure atomicity.
     * @see <a href="https://valkey.io/commands/mset/">valkey.io</a> for details.
     * @param keyValueMap A key-value map consisting of keys and their respective values to set.
     * @return A simple <code>OK</code> response.
     * @example
     *     <pre>{@code
     * String result = client.mset(Map.of("key1", "value1", "key2", "value2"}).get();
     * assert result.equals("OK"));
     * }</pre>
     */
    CompletableFuture<String> mset(Map<String, String> keyValueMap);

    /**
     * Sets multiple keys to multiple values in a single operation.
     *
     * @apiNote In cluster mode, if keys in <code>keyValueMap</code> map to different hash slots, the
     *     command will be split across these slots and executed separately for each. This means the
     *     command is atomic only at the slot level. If one or more slot-specific requests fail, the
     *     entire call will return the first encountered error, even though some requests may have
     *     succeeded while others did not. If this behavior impacts your application logic, consider
     *     splitting the request into sub-requests per slot to ensure atomicity.
     * @see <a href="https://valkey.io/commands/mset/">valkey.io</a> for details.
     * @param keyValueMap A key-value map consisting of keys and their respective values to set.
     * @return A simple <code>OK</code> response.
     * @example
     *     <pre>{@code
     * String result = client.msetBinary(Map.of(gs("key1"), gs("value1"), gs("key2"), gs("value2")}).get();
     * assert result.equals("OK"));
     * }</pre>
     */
    CompletableFuture<String> msetBinary(Map<GlideString, GlideString> keyValueMap);

    /**
     * Sets multiple keys to values if the key does not exist. The operation is atomic, and if one or
     * more keys already exist, the entire operation fails.
     *
     * @apiNote When in cluster mode, all keys in <code>keyValueMap</code> must map to the same hash
     *     slot.
     * @see <a href="https://valkey.io/commands/msetnx/">valkey.io</a> for details.
     * @param keyValueMap A key-value map consisting of keys and their respective values to set.
     * @return <code>true</code> if all keys were set. <code>false</code> if no key was set.
     * @example
     *     <pre>{@code
     * Boolean result = client.msetnx(Map.of("key1", "value1", "key2", "value2"}).get();
     * assert result;
     * }</pre>
     */
    CompletableFuture<Boolean> msetnx(Map<String, String> keyValueMap);

    /**
     * Sets multiple keys to values if the key does not exist. The operation is atomic, and if one or
     * more keys already exist, the entire operation fails.
     *
     * @apiNote When in cluster mode, all keys in <code>keyValueMap</code> must map to the same hash
     *     slot.
     * @see <a href="https://valkey.io/commands/msetnx/">valkey.io</a> for details.
     * @param keyValueMap A key-value map consisting of keys and their respective values to set.
     * @return <code>true</code> if all keys were set. <code>false</code> if no key was set.
     * @example
     *     <pre>{@code
     * Boolean result = client.msetnxBinary(Map.of(gs("key1"), gs("value1"), gs("key2"), gs("value2")}).get();
     * assert result;
     * }</pre>
     */
    CompletableFuture<Boolean> msetnxBinary(Map<GlideString, GlideString> keyValueMap);

    /**
     * Increments the number stored at <code>key</code> by one. If <code>key</code> does not exist, it
     * is set to 0 before performing the operation.
     *
     * @see <a href="https://valkey.io/commands/incr/">valkey.io</a> for details.
     * @param key The key to increment its value.
     * @return The value of <code>key</code> after the increment.
     * @example
     *     <pre>{@code
     * Long num = client.incr("key").get();
     * assert num == 5L;
     * }</pre>
     */
    CompletableFuture<Long> incr(String key);

    /**
     * Increments the number stored at <code>key</code> by one. If <code>key</code> does not exist, it
     * is set to 0 before performing the operation.
     *
     * @see <a href="https://valkey.io/commands/incr/">valkey.io</a> for details.
     * @param key The key to increment its value.
     * @return The value of <code>key</code> after the increment.
     * @example
     *     <pre>{@code
     * Long num = client.incr(gs("key")).get();
     * assert num == 5L;
     * }</pre>
     */
    CompletableFuture<Long> incr(GlideString key);

    /**
     * Increments the number stored at <code>key</code> by <code>amount</code>. If <code>key</code>
     * does not exist, it is set to 0 before performing the operation.
     *
     * @see <a href="https://valkey.io/commands/incrby/">valkey.io</a> for details.
     * @param key The key to increment its value.
     * @param amount The amount to increment.
     * @return The value of <code>key</code> after the increment.
     * @example
     *     <pre>{@code
     * Long num = client.incrBy("key", 2).get();
     * assert num == 7L;
     * }</pre>
     */
    CompletableFuture<Long> incrBy(String key, long amount);

    /**
     * Increments the number stored at <code>key</code> by <code>amount</code>. If <code>key</code>
     * does not exist, it is set to 0 before performing the operation.
     *
     * @see <a href="https://valkey.io/commands/incrby/">valkey.io</a> for details.
     * @param key The key to increment its value.
     * @param amount The amount to increment.
     * @return The value of <code>key</code> after the increment.
     * @example
     *     <pre>{@code
     * Long num = client.incrBy(gs("key"), 2).get();
     * assert num == 7L;
     * }</pre>
     */
    CompletableFuture<Long> incrBy(GlideString key, long amount);

    /**
     * Increments the string representing a floating point number stored at <code>key</code> by <code>
     * amount</code>. By using a negative increment value, the result is that the value stored at
     * <code>key</code> is decremented. If <code>key</code> does not exist, it is set to 0 before
     * performing the operation.
     *
     * @see <a href="https://valkey.io/commands/incrbyfloat/">valkey.io</a> for details.
     * @param key The key to increment its value.
     * @param amount The amount to increment.
     * @return The value of <code>key</code> after the increment.
     * @example
     *     <pre>{@code
     * Double num = client.incrByFloat("key", 0.5).get();
     * assert num == 7.5;
     * }</pre>
     */
    CompletableFuture<Double> incrByFloat(String key, double amount);

    /**
     * Increments the string representing a floating point number stored at <code>key</code> by <code>
     * amount</code>. By using a negative increment value, the result is that the value stored at
     * <code>key</code> is decremented. If <code>key</code> does not exist, it is set to 0 before
     * performing the operation.
     *
     * @see <a href="https://valkey.io/commands/incrbyfloat/">valkey.io</a> for details.
     * @param key The key to increment its value.
     * @param amount The amount to increment.
     * @return The value of <code>key</code> after the increment.
     * @example
     *     <pre>{@code
     * Double num = client.incrByFloat(gs("key"), 0.5).get();
     * assert num == 7.5;
     * }</pre>
     */
    CompletableFuture<Double> incrByFloat(GlideString key, double amount);

    /**
     * Decrements the number stored at <code>key</code> by one. If <code>key</code> does not exist, it
     * is set to 0 before performing the operation.
     *
     * @see <a href="https://valkey.io/commands/decr/">valkey.io</a> for details.
     * @param key The key to decrement its value.
     * @return The value of <code>key</code> after the decrement.
     * @example
     *     <pre>{@code
     * Long num = client.decr("key").get();
     * assert num == 4L;
     * }</pre>
     */
    CompletableFuture<Long> decr(String key);

    /**
     * Decrements the number stored at <code>key</code> by one. If <code>key</code> does not exist, it
     * is set to 0 before performing the operation.
     *
     * @see <a href="https://valkey.io/commands/decr/">valkey.io</a> for details.
     * @param key The key to decrement its value.
     * @return The value of <code>key</code> after the decrement.
     * @example
     *     <pre>{@code
     * Long num = client.decr(gs("key")).get();
     * assert num == 4L;
     * }</pre>
     */
    CompletableFuture<Long> decr(GlideString key);

    /**
     * Decrements the number stored at <code>key</code> by <code>amount</code>. If <code>key</code>
     * does not exist, it is set to 0 before performing the operation.
     *
     * @see <a href="https://valkey.io/commands/decrby/">valkey.io</a> for details.
     * @param key The key to decrement its value.
     * @param amount The amount to decrement.
     * @return The value of <code>key</code> after the decrement.
     * @example
     *     <pre>{@code
     * Long num = client.decrBy("key", 2).get();
     * assert num == 2L;
     * }</pre>
     */
    CompletableFuture<Long> decrBy(String key, long amount);

    /**
     * Decrements the number stored at <code>key</code> by <code>amount</code>. If <code>key</code>
     * does not exist, it is set to 0 before performing the operation.
     *
     * @see <a href="https://valkey.io/commands/decrby/">valkey.io</a> for details.
     * @param key The key to decrement its value.
     * @param amount The amount to decrement.
     * @return The value of <code>key</code> after the decrement.
     * @example
     *     <pre>{@code
     * Long num = client.decrBy(gs("key"), 2).get();
     * assert num == 2L;
     * }</pre>
     */
    CompletableFuture<Long> decrBy(GlideString key, long amount);

    /**
     * Returns the length of the string value stored at <code>key</code>.
     *
     * @see <a href="https://valkey.io/commands/strlen/">valkey.io</a> for details.
     * @param key The key to check its length.
     * @return The length of the string value stored at key.<br>
     *     If <code>key</code> does not exist, it is treated as an empty string, and the command
     *     returns <code>0</code>.
     * @example
     *     <pre>{@code
     * client.set("key", "GLIDE").get();
     * Long len = client.strlen("key").get();
     * assert len == 5L;
     *
     * len = client.strlen("non_existing_key").get();
     * assert len == 0L;
     * }</pre>
     */
    CompletableFuture<Long> strlen(String key);

    /**
     * Returns the length of the string value stored at <code>key</code>.
     *
     * @see <a href="https://valkey.io/commands/strlen/">valkey.io</a> for details.
     * @param key The key to check its length.
     * @return The length of the string value stored at key.<br>
     *     If <code>key</code> does not exist, it is treated as an empty string, and the command
     *     returns <code>0</code>.
     * @example
     *     <pre>{@code
     * client.set(gs("key"), gs("GLIDE")).get();
     * Long len = client.strlen(gs("key")).get();
     * assert len == 5L;
     *
     * len = client.strlen(gs("non_existing_key")).get();
     * assert len == 0L;
     * }</pre>
     */
    CompletableFuture<Long> strlen(GlideString key);

    /**
     * Overwrites part of the string stored at <code>key</code>, starting at the specified <code>
     * offset</code>, for the entire length of <code>value</code>.<br>
     * If the <code>offset</code> is larger than the current length of the string at <code>key</code>,
     * the string is padded with zero bytes to make <code>offset</code> fit. Creates the <code>key
     * </code> if it doesn't exist.
     *
     * @see <a href="https://valkey.io/commands/setrange/">valkey.io</a> for details.
     * @param key The key of the string to update.
     * @param offset The position in the string where <code>value</code> should be written.
     * @param value The string written with <code>offset</code>.
     * @return The length of the string stored at <code>key</code> after it was modified.
     * @example
     *     <pre>{@code
     * Long len = client.setrange("key", 6, "GLIDE").get();
     * assert len == 11L; // New key was created with length of 11 symbols
     * String value = client.get("key").get();
     * assert value.equals("\0\0\0\0\0\0GLIDE"); // The string was padded with zero bytes
     * }</pre>
     */
    CompletableFuture<Long> setrange(String key, int offset, String value);

    /**
     * Overwrites part of the GlideString stored at <code>key</code>, starting at the specified <code>
     * offset</code>, for the entire length of <code>value</code>.<br>
     * If the <code>offset</code> is larger than the current length of the GlideString at <code>key
     * </code>, the GlideString is padded with zero bytes to make <code>offset</code> fit. Creates the
     * <code>key
     * </code> if it doesn't exist.
     *
     * @see <a href="https://valkey.io/commands/setrange/">valkey.io</a> for details.
     * @param key The key of the GlideString to update.
     * @param offset The position in the GlideString where <code>value</code> should be written.
     * @param value The GlideString written with <code>offset</code>.
     * @return The length of the GlideString stored at <code>key</code> after it was modified.
     * @example
     *     <pre>{@code
     * Long len = client.setrange(gs("key"), 6, gs("GLIDE")).get();
     * assert len == 11L; // New key was created with length of 11 symbols
     * GlideString value = client.get(gs("key")).get();
     * assert value.equals(gs("\0\0\0\0\0\0GLIDE")); // The string was padded with zero bytes
     * }</pre>
     */
    CompletableFuture<Long> setrange(GlideString key, int offset, GlideString value);

    /**
     * Returns the substring of the string value stored at <code>key</code>, determined by the offsets
     * <code>start</code> and <code>end</code> (both are inclusive). Negative offsets can be used in
     * order to provide an offset starting from the end of the string. So <code>-1</code> means the
     * last character, <code>-2</code> the penultimate and so forth.
     *
     * @see <a href="https://valkey.io/commands/getrange/">valkey.io</a> for details.
     * @param key The key of the string.
     * @param start The starting offset.
     * @param end The ending offset.
     * @return A substring extracted from the value stored at <code>key</code>..
     * @example
     *     <pre>{@code
     * client.set("mykey", "This is a string").get();
     * String substring = client.getrange("mykey", 0, 3).get();
     * assert substring.equals("This");
     * String substring = client.getrange("mykey", -3, -1).get();
     * assert substring.equals("ing"); // extracted last 3 characters of a string
     * }</pre>
     */
    CompletableFuture<String> getrange(String key, int start, int end);

    /**
     * Returns the subGlideString of the GlideString value stored at <code>key</code>, determined by
     * the offsets <code>start</code> and <code>end</code> (both are inclusive). Negative offsets can
     * be used in order to provide an offset starting from the end of the GlideString. So <code>-1
     * </code> means the last character, <code>-2</code> the penultimate and so forth.
     *
     * @see <a href="https://valkey.io/commands/getrange/">valkey.io</a> for details.
     * @param key The key of the GlideString.
     * @param start The starting offset.
     * @param end The ending offset.
     * @return A subGlideString extracted from the value stored at <code>key</code>..
     * @example
     *     <pre>{@code
     * client.set(gs("mykey"), gs("This is a GlideString")).get();
     * GlideString subGlideString = client.getrange(gs("mykey"), 0, 3).get();
     * assert subGlideString.equals(gs("This"));
     * GlideString subGlideString = client.getrange(gs("mykey"), -3, -1).get();
     * assert subGlideString.equals(gs("ing")); // extracted last 3 characters of a GlideString
     * }</pre>
     */
    CompletableFuture<GlideString> getrange(GlideString key, int start, int end);

    /**
     * Appends a <code>value</code> to a <code>key</code>. If <code>key</code> does not exist it is
     * created and set as an empty string, so <code>APPEND</code> will be similar to {@see #set} in
     * this special case.
     *
     * @see <a href="https://valkey.io/commands/append/">valkey.io</a> for details.
     * @param key The key of the string.
     * @param value The value to append.
     * @return The length of the string after appending the value.
     * @example
     *     <pre>{@code
     * Long value = client.append("key", "value").get();
     * assert value.equals(5L);
     * }</pre>
     */
    CompletableFuture<Long> append(String key, String value);

    /**
     * Appends a <code>value</code> to a <code>key</code>. If <code>key</code> does not exist it is
     * created and set as an empty string, so <code>APPEND</code> will be similar to {@see #set} in
     * this special case.
     *
     * @see <a href="https://valkey.io/commands/append/">valkey.io</a> for details.
     * @param key The key of the string.
     * @param value The value to append.
     * @return The length of the string after appending the value.
     * @example
     *     <pre>{@code
     * Long value = client.append(gs("key"), gs("value")).get();
     * assert value.equals(5L);
     * }</pre>
     */
    CompletableFuture<Long> append(GlideString key, GlideString value);

    /**
     * Returns all the longest common subsequences combined between strings stored at <code>key1
     * </code> and <code>key2</code>.
     *
     * @since Valkey 7.0 and above.
     * @apiNote When in cluster mode, <code>key1</code> and <code>key2</code> must map to the same
     *     hash slot.
     * @see <a href="https://valkey.io/commands/lcs/">valkey.io</a> for details.
     * @param key1 The key that stores the first string.
     * @param key2 The key that stores the second string.
     * @return A <code>String</code> containing all the longest common subsequences combined between
     *     the 2 strings. An empty <code>String</code> is returned if the keys do not exist or have no
     *     common subsequences.
     * @example
     *     <pre>{@code
     * // testKey1 = abcd, testKey2 = axcd
     * String result = client.lcs("testKey1", "testKey2").get();
     * assert result.equals("acd");
     * }</pre>
     */
    CompletableFuture<String> lcs(String key1, String key2);

    /**
     * Returns all the longest common subsequences combined between strings stored at <code>key1
     * </code> and <code>key2</code>.
     *
     * @since Valkey 7.0 and above.
     * @apiNote When in cluster mode, <code>key1</code> and <code>key2</code> must map to the same
     *     hash slot.
     * @see <a href="https://valkey.io/commands/lcs/">valkey.io</a> for details.
     * @param key1 The key that stores the first string.
     * @param key2 The key that stores the second string.
     * @return A <code>String</code> containing all the longest common subsequences combined between
     *     the 2 strings. An empty <code>GlideString</code> is returned if the keys do not exist or
     *     have no common subsequences.
     * @example
     *     <pre>{@code
     * // testKey1 = abcd, testKey2 = axcd
     * GlideString result = client.lcs(gs("testKey1"), gs("testKey2")).get();
     * assert result.equals(gs("acd"));
     * }</pre>
     */
    CompletableFuture<GlideString> lcs(GlideString key1, GlideString key2);

    /**
     * Returns the total length of all the longest common subsequences between strings stored at
     * <code>key1</code> and <code>key2</code>.
     *
     * @since Valkey 7.0 and above.
     * @apiNote When in cluster mode, <code>key1</code> and <code>key2</code> must map to the same
     *     hash slot.
     * @see <a href="https://valkey.io/commands/lcs/">valkey.io</a> for details.
     * @param key1 The key that stores the first string.
     * @param key2 The key that stores the second string.
     * @return The total length of all the longest common subsequences the 2 strings.
     * @example
     *     <pre>{@code
     * // testKey1 = abcd, testKey2 = axcd
     * Long result = client.lcsLen("testKey1", "testKey2").get();
     * assert result.equals(3L);
     * }</pre>
     */
    CompletableFuture<Long> lcsLen(String key1, String key2);

    /**
     * Returns the total length of all the longest common subsequences between strings stored at
     * <code>key1</code> and <code>key2</code>.
     *
     * @since Valkey 7.0 and above.
     * @apiNote When in cluster mode, <code>key1</code> and <code>key2</code> must map to the same
     *     hash slot.
     * @see <a href="https://valkey.io/commands/lcs/">valkey.io</a> for details.
     * @param key1 The key that stores the first string.
     * @param key2 The key that stores the second string.
     * @return The length of the longest common subsequence between the 2 strings.
     * @example
     *     <pre>{@code
     * // testKey1 = abcd, testKey2 = axcd
     * Long result = client.lcsLen(gs("testKey1"), gs("testKey2")).get();
     * assert result.equals(3L);
     * }</pre>
     */
    CompletableFuture<Long> lcsLen(GlideString key1, GlideString key2);

    /**
     * Returns the indices and the total length of all the longest common subsequences between strings
     * stored at <code>key1</code> and <code>key2</code>.
     *
     * @since Valkey 7.0 and above.
     * @apiNote When in cluster mode, <code>key1</code> and <code>key2</code> must map to the same
     *     hash slot.
     * @see <a href="https://valkey.io/commands/lcs/">valkey.io</a> for details.
     * @param key1 The key that stores the first string.
     * @param key2 The key that stores the second string.
     * @return A <code>Map</code> containing the indices of the longest common subsequence between the
     *     2 strings and the total length of all the longest common subsequences. The resulting map
     *     contains two keys, "matches" and "len":
     *     <ul>
     *       <li>"len" is mapped to the total length of the all longest common subsequences between
     *           the 2 strings stored as <code>Long</code>.
     *       <li>"matches" is mapped to a three dimensional <code>Long</code> array that stores pairs
     *           of indices that represent the location of the common subsequences in the strings held
     *           by <code>key1</code> and <code>key2</code>.
     *     </ul>
     *     See example for more details.
     * @example
     *     <pre>{@code
     * client.mset(Map.of("key1", "abcd123", "key2", "bcdef123")).get();
     * Map<String, Object> response = client.lcsIdx("key1", "key2").get();
     * // the response contains data in the following format:
     * Map<String, Object> data = Map.of(
     *     "matches", new Long[][][] {
     *         {                         // the first substring match is `"123"`
     *             {4L, 6L},             // in `"key1"` it is located between indices `4` and `6`
     *             {5L, 7L}              // and in `"key2"` - in between `5` and `7`
     *         },
     *         {                         // second substring match is `"bcd"`
     *             {1L, 3L},             // in `"key1"` it is located between indices `1` and `3`
     *             {0L, 2L}              // and in `"key2"` - in between `0` and `2`
     *         }
     *     },
     *     "len", 6                      // total length of the all matches found
     * );
     * }</pre>
     */
    CompletableFuture<Map<String, Object>> lcsIdx(String key1, String key2);

    /**
     * Returns the indices and the total length of all the longest common subsequences between strings
     * stored at <code>key1</code> and <code>key2</code>.
     *
     * @since Valkey 7.0 and above.
     * @apiNote When in cluster mode, <code>key1</code> and <code>key2</code> must map to the same
     *     hash slot.
     * @see <a href="https://valkey.io/commands/lcs/">valkey.io</a> for details.
     * @param key1 The key that stores the first string.
     * @param key2 The key that stores the second string.
     * @return A <code>Map</code> containing the indices of the longest common subsequence between the
     *     2 strings and the total length of all the longest common subsequences. The resulting map
     *     contains two keys, "matches" and "len":
     *     <ul>
     *       <li>"len" is mapped to the total length of the all longest common subsequences between
     *           the 2 strings stored as <code>Long</code>.
     *       <li>"matches" is mapped to a three dimensional <code>Long</code> array that stores pairs
     *           of indices that represent the location of the common subsequences in the strings held
     *           by <code>key1</code> and <code>key2</code>.
     *     </ul>
     *     See example for more details.
     * @example
     *     <pre>{@code
     * client.mset(Map.of(gs("key1"), gs("abcd123"), gs("key2"), gs("bcdef123"))).get();
     * Map<String, Object> response = client.lcsIdx(gs("key1"), gs("key2")).get();
     * // the response contains data in the following format:
     * Map<String, Object> data = Map.of(
     *     "matches", new Long[][][] {
     *         {                         // the first substring match is `gs("123")`
     *             {4L, 6L},             // in `gs("key1")` it is located between indices `4` and `6`
     *             {5L, 7L}              // and in `gs("key2")` - in between `5` and `7`
     *         },
     *         {                         // second substring match is `gs("bcd")`
     *             {1L, 3L},             // in `gs("key1")` it is located between indices `1` and `3`
     *             {0L, 2L}              // and in `gs("key2")` - in between `0` and `2`
     *         }
     *     },
     *     "len", 6                      // total length of the all matches found
     * );
     * }</pre>
     */
    CompletableFuture<Map<String, Object>> lcsIdx(GlideString key1, GlideString key2);

    /**
     * Returns the indices and the total length of all the longest common subsequences between strings
     * stored at <code>key1</code> and <code>key2</code>.
     *
     * @since Valkey 7.0 and above.
     * @apiNote When in cluster mode, <code>key1</code> and <code>key2</code> must map to the same
     *     hash slot.
     * @see <a href="https://valkey.io/commands/lcs/">valkey.io</a> for details.
     * @param key1 The key that stores the first string.
     * @param key2 The key that stores the second string.
     * @param minMatchLen The minimum length of matches to include in the result.
     * @return A <code>Map</code> containing the indices of the longest common subsequence between the
     *     2 strings and the total length of all the longest common subsequences. The resulting map
     *     contains two keys, "matches" and "len":
     *     <ul>
     *       <li>"len" is mapped to the total length of the all longest common subsequences between
     *           the 2 strings stored as <code>Long</code>. This value doesn't count towards the
     *           <code>minMatchLen</code> filter.
     *       <li>"matches" is mapped to a three dimensional <code>Long</code> array that stores pairs
     *           of indices that represent the location of the common subsequences in the strings held
     *           by <code>key1</code> and <code>key2</code>.
     *     </ul>
     *     See example for more details.
     * @example
     *     <pre>{@code
     * client.mset(Map.of("key1", "abcd123", "key2", "bcdef123")).get();
     * Map<String, Object> response = client.lcsIdx("key1", "key2", 2).get();
     * // the response contains data in the following format:
     * Map<String, Object> data = Map.of(
     *     "matches", new Long[][][] {
     *         {                         // the first substring match is `"123"`
     *             {4L, 6L},             // in `"key1"` it is located between indices `4` and `6`
     *             {5L, 7L}              // and in `"key2"` - in between `5` and `7`
     *         },
     *         {                         // second substring match is `"bcd"`
     *             {1L, 3L},             // in `"key1"` it is located between indices `1` and `3`
     *             {0L, 2L}              // and in `"key2"` - in between `0` and `2`
     *         }
     *     },
     *     "len", 6                      // total length of the all matches found
     * );
     * }</pre>
     */
    CompletableFuture<Map<String, Object>> lcsIdx(String key1, String key2, long minMatchLen);

    /**
     * Returns the indices and the total length of all the longest common subsequences between strings
     * stored at <code>key1</code> and <code>key2</code>.
     *
     * @since Valkey 7.0 and above.
     * @apiNote When in cluster mode, <code>key1</code> and <code>key2</code> must map to the same
     *     hash slot.
     * @see <a href="https://valkey.io/commands/lcs/">valkey.io</a> for details.
     * @param key1 The key that stores the first string.
     * @param key2 The key that stores the second string.
     * @param minMatchLen The minimum length of matches to include in the result.
     * @return A <code>Map</code> containing the indices of the longest common subsequence between the
     *     2 strings and the total length of all the longest common subsequences. The resulting map
     *     contains two keys, "matches" and "len":
     *     <ul>
     *       <li>"len" is mapped to the total length of the all longest common subsequences between
     *           the 2 strings stored as <code>Long</code>. This value doesn't count towards the
     *           <code>minMatchLen</code> filter.
     *       <li>"matches" is mapped to a three dimensional <code>Long</code> array that stores pairs
     *           of indices that represent the location of the common subsequences in the strings held
     *           by <code>key1</code> and <code>key2</code>.
     *     </ul>
     *     See example for more details.
     * @example
     *     <pre>{@code
     * client.mset(Map.of(gs("key1"), gs("abcd123"), gs("key2"), gs("bcdef123"))).get();
     * Map<String, Object> response = client.lcsIdx(gs("key1"), gs("key2"), 2).get();
     * // the response contains data in the following format:
     * Map<String, Object> data = Map.of(
     *     "matches", new Long[][][] {
     *         {                         // the first substring match is `gs("123")`
     *             {4L, 6L},             // in `gs("key1")` it is located between indices `4` and `6`
     *             {5L, 7L}              // and in `gs("key2")` - in between `5` and `7`
     *         },
     *         {                         // second substring match is `gs("bcd")`
     *             {1L, 3L},             // in `gs("key1")` it is located between indices `1` and `3`
     *             {0L, 2L}              // and in `gs("key2")` - in between `0` and `2`
     *         }
     *     },
     *     "len", 6                      // total length of the all matches found
     * );
     * }</pre>
     */
    CompletableFuture<Map<String, Object>> lcsIdx(
            GlideString key1, GlideString key2, long minMatchLen);

    /**
     * Returns the indices and lengths of the longest common subsequences between strings stored at
     * <code>key1</code> and <code>key2</code>.
     *
     * @since Valkey 7.0 and above.
     * @apiNote When in cluster mode, <code>key1</code> and <code>key2</code> must map to the same
     *     hash slot.
     * @see <a href="https://valkey.io/commands/lcs/">valkey.io</a> for details.
     * @param key1 The key that stores the first string.
     * @param key2 The key that stores the second string.
     * @return A <code>Map</code> containing the indices of the longest common subsequence between the
     *     2 strings and the lengths of the longest common subsequences. The resulting map contains
     *     two keys, "matches" and "len":
     *     <ul>
     *       <li>"len" is mapped to the total length of the all longest common subsequences between
     *           the 2 strings stored as <code>Long</code>.
     *       <li>"matches" is mapped to a three dimensional array that stores pairs of indices that
     *           represent the location of the common subsequences in the strings held by <code>key1
     *           </code> and <code>key2</code> and the match length.
     *     </ul>
     *     See example for more details.
     * @example
     *     <pre>{@code
     * client.mset(Map.of("key1", "abcd1234", "key2", "bcdef1234")).get();
     * Map<String, Object> response = client.lcsIdxWithMatchLen("key1", "key2").get();
     * // the response contains data in the following format:
     * Map<String, Object> data = Map.of(
     *     "matches", new Object[][] {
     *         {                                    // the first substring match is `"1234"`
     *             new Long[] {4L, 7L},             // in `"key1"` it is located between indices `4` and `7`
     *             new Long[] {5L, 8L},             // and in `"key2"` - in between `5` and `8`
     *             4L                               // the match length
     *         },
     *         {                                    // second substring match is `"bcd"`
     *             new Long[] {1L, 3L},             // in `"key1"` it is located between indices `1` and `3`
     *             new Long[] {0L, 2L},             // and in `"key2"` - in between `0` and `2`
     *             3L                               // the match length
     *         }
     *     },
     *     "len", 6                                 // total length of the all matches found
     * );
     * }</pre>
     */
    CompletableFuture<Map<String, Object>> lcsIdxWithMatchLen(String key1, String key2);

    /**
     * Returns the indices and lengths of the longest common subsequences between strings stored at
     * <code>key1</code> and <code>key2</code>.
     *
     * @since Valkey 7.0 and above.
     * @apiNote When in cluster mode, <code>key1</code> and <code>key2</code> must map to the same
     *     hash slot.
     * @see <a href="https://valkey.io/commands/lcs/">valkey.io</a> for details.
     * @param key1 The key that stores the first string.
     * @param key2 The key that stores the second string.
     * @return A <code>Map</code> containing the indices of the longest common subsequence between the
     *     2 strings and the lengths of the longest common subsequences. The resulting map contains
     *     two keys, "matches" and "len":
     *     <ul>
     *       <li>"len" is mapped to the total length of the all longest common subsequences between
     *           the 2 strings stored as <code>Long</code>.
     *       <li>"matches" is mapped to a three dimensional array that stores pairs of indices that
     *           represent the location of the common subsequences in the strings held by <code>key1
     *           </code> and <code>key2</code> and the match length.
     *     </ul>
     *     See example for more details.
     * @example
     *     <pre>{@code
     * client.mset(Map.of(gs("key1"), gs("abcd1234"), gs("key2"), gs("bcdef1234"))).get();
     * Map<String, Object> response = client.lcsIdxWithMatchLen(gs("key1"), gs("key2")).get();
     * // the response contains data in the following format:
     * Map<String, Object> data = Map.of(
     *     "matches", new Object[][] {
     *         {                                    // the first substring match is `gs("1234")`
     *             new Long[] {4L, 7L},             // in `gs("key1")` it is located between indices `4` and `7`
     *             new Long[] {5L, 8L},             // and in `gs("key2")` - in between `5` and `8`
     *             4L                               // the match length
     *         },
     *         {                                    // second substring match is `"bcd"`
     *             new Long[] {1L, 3L},             // in `gs("key1")` it is located between indices `1` and `3`
     *             new Long[] {0L, 2L},             // and in `gs("key2")` - in between `0` and `2`
     *             3L                               // the match length
     *         }
     *     },
     *     "len", 6                                 // total length of the all matches found
     * );
     * }</pre>
     */
    CompletableFuture<Map<String, Object>> lcsIdxWithMatchLen(GlideString key1, GlideString key2);

    /**
     * Returns the indices and lengths of the longest common subsequences between strings stored at
     * <code>key1</code> and <code>key2</code>.
     *
     * @since Valkey 7.0 and above.
     * @apiNote When in cluster mode, <code>key1</code> and <code>key2</code> must map to the same
     *     hash slot.
     * @see <a href="https://valkey.io/commands/lcs/">valkey.io</a> for details.
     * @param key1 The key that stores the first string.
     * @param key2 The key that stores the second string.
     * @param minMatchLen The minimum length of matches to include in the result.
     * @return A <code>Map</code> containing the indices of the longest common subsequence between the
     *     2 strings and the total length of all the longest common subsequences. The resulting map
     *     contains two keys, "matches" and "len":
     *     <ul>
     *       <li>"len" is mapped to the total length of the all longest common subsequences between
     *           the 2 strings stored as <code>Long</code>. This value doesn't count towards the
     *           <code>minMatchLen</code> filter.
     *       <li>"matches" is mapped to a three dimensional array that stores pairs of indices that
     *           represent the location of the common subsequences in the strings held by <code>key1
     *           </code> and <code>key2</code> and the match length.
     *     </ul>
     *     See example for more details.
     * @example
     *     <pre>{@code
     * client.mset(Map.of("key1", "abcd1234", "key2", "bcdef1234")).get();
     * Map<String, Object> response = client.lcsIdxWithMatchLen("key1", "key2", 2).get();
     * // the response contains data in the following format:
     * Map<String, Object> data = Map.of(
     *     "matches", new Object[][] {
     *         {                                    // the first substring match is `"1234"`
     *             new Long[] {4L, 7L},             // in `"key1"` it is located between indices `4` and `7`
     *             new Long[] {5L, 8L},             // and in `"key2"` - in between `5` and `8`
     *             4L                               // the match length
     *         },
     *         {                                    // second substring match is `"bcd"`
     *             new Long[] {1L, 3L},             // in `"key1"` it is located between indices `1` and `3`
     *             new Long[] {0L, 2L},             // and in `"key2"` - in between `0` and `2`
     *             3L                               // the match length
     *         }
     *     },
     *     "len", 6                                 // total length of the all matches found
     * );
     * }</pre>
     */
    CompletableFuture<Map<String, Object>> lcsIdxWithMatchLen(
            String key1, String key2, long minMatchLen);

    /**
     * Returns the indices and lengths of the longest common subsequences between strings stored at
     * <code>key1</code> and <code>key2</code>.
     *
     * @since Valkey 7.0 and above.
     * @apiNote When in cluster mode, <code>key1</code> and <code>key2</code> must map to the same
     *     hash slot.
     * @see <a href="https://valkey.io/commands/lcs/">valkey.io</a> for details.
     * @param key1 The key that stores the first string.
     * @param key2 The key that stores the second string.
     * @param minMatchLen The minimum length of matches to include in the result.
     * @return A <code>Map</code> containing the indices of the longest common subsequence between the
     *     2 strings and the total length of all the longest common subsequences. The resulting map
     *     contains two keys, "matches" and "len":
     *     <ul>
     *       <li>"len" is mapped to the total length of the all longest common subsequences between
     *           the 2 strings stored as <code>Long</code>. This value doesn't count towards the
     *           <code>minMatchLen</code> filter.
     *       <li>"matches" is mapped to a three dimensional array that stores pairs of indices that
     *           represent the location of the common subsequences in the strings held by <code>key1
     *           </code> and <code>key2</code> and the match length.
     *     </ul>
     *     See example for more details.
     * @example
     *     <pre>{@code
     * client.mset(Map.of(gs("key1"), gs("abcd1234"), gs("key2"), gs("bcdef1234"))).get();
     * Map<String, Object> response = client.lcsIdxWithMatchLen(gs("key1"), gs("key2"), 2).get();
     * // the response contains data in the following format:
     * Map<String, Object> data = Map.of(
     *     "matches", new Object[][] {
     *         {                                    // the first substring match is `gs("1234")`
     *             new Long[] {4L, 7L},             // in `gs("key1")` it is located between indices `4` and `7`
     *             new Long[] {5L, 8L},             // and in `gs("key2")` - in between `5` and `8`
     *             4L                               // the match length
     *         },
     *         {                                    // second substring match is `"bcd"`
     *             new Long[] {1L, 3L},             // in `gs("key1")` it is located between indices `1` and `3`
     *             new Long[] {0L, 2L},             // and in `gs("key2")` - in between `0` and `2`
     *             3L                               // the match length
     *         }
     *     },
     *     "len", 6                                 // total length of the all matches found
     * );
     * }</pre>
     */
    CompletableFuture<Map<String, Object>> lcsIdxWithMatchLen(
            GlideString key1, GlideString key2, long minMatchLen);
}
