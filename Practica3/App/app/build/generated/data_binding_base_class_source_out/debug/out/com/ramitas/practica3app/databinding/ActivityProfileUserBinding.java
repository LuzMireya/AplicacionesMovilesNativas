// Generated by view binder compiler. Do not edit!
package com.ramitas.practica3app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ramitas.practica3app.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityProfileUserBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView emailTextView;

  @NonNull
  public final Button logoutButton;

  @NonNull
  public final ImageView profileImageView;

  @NonNull
  public final Button updateButton;

  @NonNull
  public final TextView usernameTextView;

  private ActivityProfileUserBinding(@NonNull LinearLayout rootView,
      @NonNull TextView emailTextView, @NonNull Button logoutButton,
      @NonNull ImageView profileImageView, @NonNull Button updateButton,
      @NonNull TextView usernameTextView) {
    this.rootView = rootView;
    this.emailTextView = emailTextView;
    this.logoutButton = logoutButton;
    this.profileImageView = profileImageView;
    this.updateButton = updateButton;
    this.usernameTextView = usernameTextView;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityProfileUserBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityProfileUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_profile_user, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityProfileUserBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.emailTextView;
      TextView emailTextView = ViewBindings.findChildViewById(rootView, id);
      if (emailTextView == null) {
        break missingId;
      }

      id = R.id.logoutButton;
      Button logoutButton = ViewBindings.findChildViewById(rootView, id);
      if (logoutButton == null) {
        break missingId;
      }

      id = R.id.profileImageView;
      ImageView profileImageView = ViewBindings.findChildViewById(rootView, id);
      if (profileImageView == null) {
        break missingId;
      }

      id = R.id.updateButton;
      Button updateButton = ViewBindings.findChildViewById(rootView, id);
      if (updateButton == null) {
        break missingId;
      }

      id = R.id.usernameTextView;
      TextView usernameTextView = ViewBindings.findChildViewById(rootView, id);
      if (usernameTextView == null) {
        break missingId;
      }

      return new ActivityProfileUserBinding((LinearLayout) rootView, emailTextView, logoutButton,
          profileImageView, updateButton, usernameTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
