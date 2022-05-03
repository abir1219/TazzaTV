package com.textifly.tazzatv.ui.Home.AllNews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.textifly.tazzatv.R;
import com.textifly.tazzatv.databinding.FragmentAllNewsBinding;
import com.textifly.tazzatv.ui.Home.AllNews.Adapter.AllNewsAdapter;
import com.textifly.tazzatv.ui.Home.AllNews.Model.AllNewsNewsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllNewsFragment extends Fragment {
    FragmentAllNewsBinding binding;
    //List<AllNewsNewsModel> modelList;
    List<Object> modelList;

    public static final int ITEM_PER_ADD = 4;
    public static final String BANNER_ADD_ID = "ca-app-pub-3940256099942544~3347511713";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAllNewsBinding.inflate(inflater, container, false);

        setLayout();
        loadNews();
        getBannerItems();
        loadBanner();
        return binding.getRoot();
    }

    private void getBannerItems() {
        for (int i = 0; i < modelList.size(); i += ITEM_PER_ADD) {
            AdView adView = new AdView(getActivity());
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId(BANNER_ADD_ID);
            modelList.add(i,adView);
        }
    }

    private void loadBanner() {
        for (int i = 0; i < modelList.size(); i++) {
            Object item = modelList.get(i);
            if(item instanceof AdView){
                final AdView adView = (AdView) item;
                adView.loadAd(new AdRequest.Builder().build());
            }
        }
    }

    private void setLayout() {
        binding.rvAllNews.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }

    private void loadNews() {
        modelList = new ArrayList<>();
        String url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=ac40bae0d9d6400384d7a638071c1191";
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ALL_NEWS_RES",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("status").equalsIgnoreCase("ok")) {
                        JSONArray array = jsonObject.getJSONArray("articles");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            String title = String.valueOf(Html.fromHtml(object.getString("title")));
                            String urlToImage = object.getString("urlToImage");

                            modelList.add(new AllNewsNewsModel(title, urlToImage));
                        }

                        AllNewsAdapter adapter = new AllNewsAdapter(modelList, getActivity());
                        binding.rvAllNews.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("User-Agent", "Mozilla/5.0");
                return header;
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getActivity()).add(sr);

    }
}