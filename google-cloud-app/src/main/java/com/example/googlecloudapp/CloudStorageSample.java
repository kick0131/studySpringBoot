package com.example.googlecloudapp;

import org.springframework.context.annotation.Configuration;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Configuration
public class CloudStorageSample {
    private static final String BUCKET = "dataflow-ingest";
    // バケットのどの階層から取得するか、一番親階層は空文字
    private static final String PREFIX = "";

    public void listFiles() {
        // ディレクトリ指定でオブジェクトリストを表示する

        Storage storage = StorageOptions.getDefaultInstance().getService();

        Bucket bucket = storage.get(BUCKET);
        System.out.println(String.format("bucket name : %s", bucket.getName()));

        Storage.BlobListOption option = Storage.BlobListOption.prefix(PREFIX);
        Page<Blob> blobs = bucket.list(option);
        for (Blob blob : blobs.iterateAll()) {
            System.out.println(blob.getName());
        }
    }
}
