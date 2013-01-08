/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vial.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MultiMap<K, V> {

    private final Map<K, List<V>> delegate = new HashMap<K, List<V>>();

    public int size() {
        return delegate.size();
    }

    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    public synchronized boolean containsKey(Object o) {
        return delegate.containsKey(o);
    }

    public boolean containsValue(Object o) {
        return false;
    }

    public Collection<V> get(Object o) {
        return null;
    }

    public synchronized V put(K k, V v) {
        if (delegate.containsKey(k)) {
            delegate.get(k).add(v);
        } else {
            List<V> list = new LinkedList<V>();
            list.add(v);
            delegate.put(k, list);
        }
        return v;
    }

    public synchronized List<V> remove(Object o) {
        return delegate.remove(o);
    }

    public synchronized void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        delegate.clear();
    }

    public Set<K> keySet() {
        return delegate.keySet();
    }

    public List<V> values() {
        List<V> result = new LinkedList<V>();
        for (K key : keySet()) {
            result.addAll(get(key));
        }
        return result;
    }

    public Set<Map.Entry<K, List<V>>> entrySet() {
        return delegate.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MultiMap that = (MultiMap) o;

        if (delegate != null ? !delegate.equals(that.delegate) : that.delegate != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return delegate != null ? delegate.hashCode() : 0;
    }
}
