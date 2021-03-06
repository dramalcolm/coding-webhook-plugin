/**
 * Jenkins plugin for Coding https://coding.net
 *
 * Copyright (c) 2016-2018 Shuanglei Tao <tsl0922@gmail.com>
 * Copyright (c) 2016-present, Coding, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.coding.jenkins.plugin.bean;

import lombok.Data;
import net.coding.jenkins.plugin.model.Repository;
import net.coding.jenkins.plugin.model.User;
import net.coding.jenkins.plugin.model.event.CodingBaseEvent;
import net.coding.jenkins.plugin.model.event.MergeRequest;
import net.coding.jenkins.plugin.model.event.Push;

@Data
public class WebHookTask {

    public static final String EVENT_PUSH = "push";
    public static final String EVENT_MERGE_REQUEST = "merge request";

    private Push push;
    private MergeRequest mergeRequest;
    private String event;
    private String signature;
    private String version;
    private String requestBody;
    private boolean parseSuccess = false;

    public Repository getRepository() {
        CodingBaseEvent baseEvent = getBaseEvent();
        if (baseEvent == null) {
            return null;
        }
        return baseEvent.getRepository();
    }

    public User getSender() {
        return getBaseEvent().getSender();
    }

    public CodingBaseEvent getBaseEvent() {
        switch (event) {
            case EVENT_PUSH:
                return push;
            case EVENT_MERGE_REQUEST:
                return mergeRequest;
            default:
                return null;
        }
    }
}
