return new User(
    user.getUsername(), 
    user.getPassword(), 
    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
);