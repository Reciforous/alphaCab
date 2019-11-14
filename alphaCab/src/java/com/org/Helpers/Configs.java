package com.org.Helpers;

/* TODO: Move to git

   WHATS NEW:
        final String url_prefix, type: String (this is used for redirects)
 */

/**
 * Class for storing program configurations
 */
public class Configs {
    // Used in the Functions.redirect and several jsp pages to prefix url, this changes in case of glassfish to
    // /alphacab/
    public static final String url_prefix = "/";
    public static final String file_upload_dir = "/uploads";
}
